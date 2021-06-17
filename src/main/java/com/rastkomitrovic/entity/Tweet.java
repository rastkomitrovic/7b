package com.rastkomitrovic.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "tweet")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tweet_id")
    private Long tweetId;

    @Column(name = "tweet_body")
    private String tweetBody;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date dateCreated;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "tweet_hash_tag", joinColumns = @JoinColumn(name = "tweet_id"), inverseJoinColumns = @JoinColumn(name = "hash_tag_name"))
    private Set<HashTag> hashTags;

    public Tweet() {

    }

    public Tweet(long tweetId, String tweetBody, Date dateCreated, User user, Set<HashTag> hashTags) {
        this.tweetId = tweetId;
        this.tweetBody = tweetBody;
        this.dateCreated = dateCreated;
        this.user = user;
        if (hashTags != null)
            this.hashTags = hashTags;
        else
            this.hashTags = new HashSet<>();
    }

    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }

    public String getTweetBody() {
        return tweetBody;
    }

    public void setTweetBody(String tweetBody) {
        this.tweetBody = tweetBody;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<HashTag> getHashTags() {
        return hashTags;
    }

    public void setHashTags(Set<HashTag> hashTags) {
        this.hashTags = hashTags;
    }
}
