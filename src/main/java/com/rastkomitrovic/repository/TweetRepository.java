package com.rastkomitrovic.repository;

import com.rastkomitrovic.entity.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TweetRepository extends PagingAndSortingRepository<Tweet, Long> {

    @Query(value = "select t from Tweet t join t.hashTags tag where t.user.username in (:username) and tag.hashTagName in (:hashTag)")
   //@Query(value = "Select distinct * from tweet t inner join tweet_hash_tag on t.tweet_id = tweet_hash_tag.tweet_id inner join hash_tag on tweet_hash_tag.hash_tag_name = hash_tag.hash_tag_name inner join tweets_user on tweets_user.username = t.username where tweets_user.username in (:username) or tweet_hash_tag.hash_tag_name in (:hashTag)", nativeQuery = true)
    Page<Tweet> findTweetsByUsernamesAndHashTags(@Param("username") List<String> username, @Param("hashTag") List<String> hashTag, Pageable pageable);

    @Query("select distinct t from Tweet t where t.user.username in (:username)")
    Page<Tweet> findTweetsByUsernames(@Param("username") List<String> username, Pageable pageable);

    @Query("select distinct t from Tweet t join t.hashTags tag where tag.hashTagName in (:hashTag)")
    Page<Tweet> findTweetsByHashTags(@Param("hashTag") List<String> hashTag, Pageable pageable);
}
