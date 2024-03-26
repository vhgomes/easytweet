package com.vhgomes.easytweet.repositories;

import com.vhgomes.easytweet.models.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetweetRepository extends JpaRepository<Retweet, Long> {
}
