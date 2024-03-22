package com.vhgomes.easytweet.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tb_tweets")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tweet_id")
    private long tweetId;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    private String content;

    @CreationTimestamp
    private Instant createdAt;

    public Tweet(long tweetId, User user, String content, Instant createdAt) {
        this.tweetId = tweetId;
        this.user = user;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Tweet() {
    }

    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
