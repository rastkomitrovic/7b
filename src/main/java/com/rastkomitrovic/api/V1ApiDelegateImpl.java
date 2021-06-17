package com.rastkomitrovic.api;

import com.rastkomitrovic.model.PostTweetReq;
import com.rastkomitrovic.model.TweetResp;
import com.rastkomitrovic.model.TweetsPageResp;
import com.rastkomitrovic.service.TweetService;
import com.rastkomitrovic.utils.TweetsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class V1ApiDelegateImpl implements V1ApiDelegate {

    @Resource
    private TweetService tweetService;

    private final Logger logger = LoggerFactory.getLogger(V1ApiDelegateImpl.class);


    @Override
    public ResponseEntity<TweetResp> v1TweetsPost(PostTweetReq postTweetReq, String xUsername) {
        try {
            return new ResponseEntity<>(tweetService.postTweet(postTweetReq, xUsername), HttpStatus.CREATED);
        } catch (TweetsException ex) {
            logger.error(ex.getError().getMessage());
            return new ResponseEntity(ex.getError(), HttpStatus.valueOf(ex.getError().getHttpCode()));
        }
    }

    @Override
    public ResponseEntity<TweetResp> v1TweetsTweetIdDelete(String tweetId, String xUsername) {
        try {
            return ResponseEntity.ok(tweetService.deleteTweet(tweetId, xUsername));
        } catch (TweetsException ex) {
            logger.error(ex.getError().getMessage());
            return new ResponseEntity(ex.getError(), HttpStatus.valueOf(ex.getError().getHttpCode()));
        }
    }

    @Override
    public ResponseEntity<TweetsPageResp> v1TweetsGet(String xUsername, List<String> hashTag, List<String> username, Integer limit, Integer offset) {
        try {
            return ResponseEntity.ok(tweetService.getTweetsPage(xUsername, hashTag, username, limit, offset));
        } catch (TweetsException ex) {
            logger.error(ex.getError().getMessage());
            return new ResponseEntity(ex.getError(), HttpStatus.valueOf(ex.getError().getHttpCode()));
        }
    }
}
