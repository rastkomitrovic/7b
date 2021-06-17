package com.rastkomitrovic.mapper;

import com.rastkomitrovic.utils.Constants;
import com.rastkomitrovic.entity.HashTag;
import com.rastkomitrovic.entity.Tweet;
import com.rastkomitrovic.entity.User;
import com.rastkomitrovic.model.PostTweetReq;
import com.rastkomitrovic.model.TweetResp;
import com.rastkomitrovic.model.TweetsPageResp;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URI;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetMapper {

    @Resource
    private HashTagMapper hashTagMapper;

    public Tweet mapToEntity(PostTweetReq postTweetReq, String username) {
        return new Tweet(
                -1,
                postTweetReq.getTweetBody(),
                new Date(),
                new User(username),
                postTweetReq
                        .getHashTags()
                        .get()
                        .stream()
                        .map(HashTag::new)
                        .collect(Collectors.toSet())
        );
    }

    public TweetResp mapToDto(Tweet tweet) {
        TweetResp tweetResp = new TweetResp();

        tweetResp.setTweetId(Long.toString(tweet.getTweetId()));
        tweetResp.setTweetBody(tweet.getTweetBody());
        tweetResp.setCreatedAt(tweet.getDateCreated());
        tweetResp.setHashTags(hashTagMapper.mapListToDto(tweet.getHashTags()));
        tweetResp.setCreatedBy(tweet.getUser().getUsername());

        return tweetResp;
    }

    public TweetsPageResp mapListToPageDto(Page<Tweet> page, List<String> hashTags, List<String> usernames, Integer limit, Integer offset) {
        TweetsPageResp tweetsPageResp = new TweetsPageResp();

        List<TweetResp> tweets = page.get().map(this::mapToDto).collect(Collectors.toList());
        tweetsPageResp.setTweets(tweets);

        if (!page.isLast()) {
            StringBuilder sb = new StringBuilder();
            sb.append(Constants.API_BASE)
                    .append("?");

            if (hashTags != null && !hashTags.isEmpty())
                sb.append("?hashTag=").append(String.join(", ", hashTags));
            if (usernames != null && !usernames.isEmpty())
                sb.append("&username=").append(String.join(", ", usernames));
            sb.append("&limit=").append(limit);
            sb.append("&offset=").append(offset + 1);
            tweetsPageResp.setNextPage(JsonNullable.of(URI.create(sb.toString())));
        }
        return tweetsPageResp;
    }
}
