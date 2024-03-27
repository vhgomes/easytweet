package com.vhgomes.easytweet.repositories;

import com.vhgomes.easytweet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    @Query("SELECT DISTINCT followed.userId FROM User user JOIN user.followedUsers followed WHERE user.userId = :userId")
    List<UUID> findFollowedUserIds(UUID userId);
}
