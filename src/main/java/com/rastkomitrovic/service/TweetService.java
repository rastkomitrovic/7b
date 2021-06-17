package com.rastkomitrovic.service;

import com.rastkomitrovic.entity.Tweet;
import com.rastkomitrovic.mapper.HashTagMapper;
import com.rastkomitrovic.mapper.TweetMapper;
import com.rastkomitrovic.model.Error;
import com.rastkomitrovic.model.PostTweetReq;
import com.rastkomitrovic.model.TweetResp;
import com.rastkomitrovic.model.TweetsPageResp;
import com.rastkomitrovic.repository.TweetRepository;
import com.rastkomitrovic.utils.TweetsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TweetService {

    @Resource
    private TweetRepository tweetRepository;

    @Resource
    private UserService userService;

    @Resource
    private TweetMapper tweetMapper;


    private final Logger logger = LoggerFactory.getLogger(TweetService.class);

    public TweetsPageResp getTweetsPage(String xUsername, List<String> hashTag, List<String> username, Integer limit, Integer offset) throws TweetsException {
        validate(xUsername);
        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by("dateCreated").descending());
        Page<Tweet> tweetPage;

        if (hashTag != null && !hashTag.isEmpty() && username != null && !username.isEmpty())
            tweetPage = tweetRepository.findTweetsByUsernamesAndHashTags(username, hashTag, pageRequest);
        else if ((hashTag == null || hashTag.isEmpty()))
            tweetPage = tweetRepository.findTweetsByUsernames(username, pageRequest);
        else
            tweetPage = tweetRepository.findTweetsByHashTags(hashTag, pageRequest);

        return tweetMapper.mapListToPageDto(tweetPage, hashTag, username, limit, offset);
    }

    public TweetResp postTweet(PostTweetReq postTweetReq, String xUsername) throws TweetsException {
        validate(xUsername);
        return tweetMapper.mapToDto(tweetRepository.save(tweetMapper.mapToEntity(postTweetReq, xUsername)));
    }

    public TweetResp deleteTweet(String tweetIdString, String xUsername) throws TweetsException {
        validate(xUsername);
        try {
            Long tweetId = Long.parseLong(tweetIdString);
            Optional<Tweet> tweet = tweetRepository.findById(Long.parseLong(tweetIdString));
            if (tweet.isPresent()) {
                if (tweet.get().getUser().getUsername().equals(xUsername)) {
                    tweetRepository.deleteById(tweetId);
                    return tweetMapper.mapToDto(tweet.get());
                } else
                    throw new TweetsException(createError(HttpStatus.FORBIDDEN, "Cant delete other users tweet"));
            }
            throw new TweetsException(createError(HttpStatus.NOT_FOUND, "Tweet not found"));
        } catch (NumberFormatException ex) {
            logger.error("Error formatting tweetIdString:" + tweetIdString + " to Long");
            logger.error(ex.getLocalizedMessage());
            throw new TweetsException(createError(HttpStatus.BAD_REQUEST, "Error: Passed characters for tweetId (only numbers can be used)"));
        }
    }


    private void validate(String xUsername) throws TweetsException {
        if (xUsername == null || xUsername.isEmpty() || !userService.existsByUsername(xUsername))
            throw new TweetsException(createError(HttpStatus.UNAUTHORIZED, "User for the passed username in the header not found"));
    }

    private Error createError(HttpStatus httpStatus, String message) {
        Error error = new Error();
        error.setHttpCode(httpStatus.value());
        error.setMessage(message);
        return error;
    }

}
