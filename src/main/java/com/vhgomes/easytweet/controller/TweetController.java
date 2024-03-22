package com.vhgomes.easytweet.controller;

import com.vhgomes.easytweet.dtos.CreateTweetDTO;
import com.vhgomes.easytweet.dtos.FeedDTO;
import com.vhgomes.easytweet.dtos.FeedItemDTO;
import com.vhgomes.easytweet.models.Tweet;
import com.vhgomes.easytweet.repositories.TweetRepository;
import com.vhgomes.easytweet.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
public class TweetController {
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public TweetController(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/tweets")
    public ResponseEntity<Void> createTweet(@RequestBody CreateTweetDTO createTweetDTO, JwtAuthenticationToken jwtAuthenticationToken) {
        var user = userRepository.findById(UUID.fromString(jwtAuthenticationToken.getName()));
        var tweet = new Tweet();
        tweet.setContent(createTweetDTO.content());
        tweet.setUser(user.get());
        tweetRepository.save(tweet);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/tweets")
    public ResponseEntity<FeedDTO> feed(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        var tweets = tweetRepository.findAll(PageRequest.of(page, pageSize, Sort.Direction.DESC, "createdAt"))
                .map(tweet -> new FeedItemDTO(
                        tweet.getTweetId(),
                        tweet.getContent(),
                        tweet.getUser().getUsername()
                ));

        return ResponseEntity.ok(new FeedDTO(tweets.getContent(),
                page,
                pageSize,
                tweets.getTotalPages(),
                tweets.getTotalElements()));
    }

    @DeleteMapping("/tweets/{id}")
    public ResponseEntity<Void> deleteTweetByID(@PathVariable("id") long tweetId, JwtAuthenticationToken jwtAuthenticationToken) {
        var tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (tweet.getUser().getUserId().equals(UUID.fromString(jwtAuthenticationToken.getName()))) {
            tweetRepository.deleteById(tweetId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
