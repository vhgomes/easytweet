package com.vhgomes.easytweet.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tb_retweet")
public class Retweet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "retweet_id")
    private long retweetId;

    @JoinColumn(name = "tweet_id")
    @OneToOne
    private Tweet tweetId;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @CreationTimestamp
    private Instant createdAt;

    public Retweet(long retweetId, Tweet tweetId, User user, Instant createdAt) {
        this.retweetId = retweetId;
        this.tweetId = tweetId;
        this.user = user;
        this.createdAt = createdAt;
    }

    public Retweet() {

    }

    public long getRetweetId() {
        return retweetId;
    }

    public void setRetweetId(long retweetId) {
        this.retweetId = retweetId;
    }

    public Tweet getTweetId() {
        return tweetId;
    }

    public void setTweetId(Tweet tweetId) {
        this.tweetId = tweetId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
