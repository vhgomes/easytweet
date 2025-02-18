package com.vhgomes.easytweet.controller;

import com.vhgomes.easytweet.dtos.CreateUserDTO;
import com.vhgomes.easytweet.models.Role;
import com.vhgomes.easytweet.models.User;
import com.vhgomes.easytweet.repositories.RoleRepository;
import com.vhgomes.easytweet.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/users")
    @Transactional
    public ResponseEntity<Void> newUser(@RequestBody CreateUserDTO createUserDTO) {
        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());
        var userExist = userRepository.findByUsername(createUserDTO.username());

        if (userExist.isPresent()) throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);

        var user = new User();
        user.setUsername(createUserDTO.username());
        user.setPassword(passwordEncoder.encode(createUserDTO.password()));
        user.setRoles(Set.of(basicRole));

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> listUsers() {
        var allUsers = userRepository.findAll();
        return ResponseEntity.ok(allUsers);
    }

    @PostMapping("{userId}/users/follow")
    public ResponseEntity<Void> followUser(@PathVariable UUID userId, JwtAuthenticationToken jwtAuthenticationToken) {
        var user = userRepository.findById(UUID.fromString(jwtAuthenticationToken.getName())).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var followedUser = userRepository.findById(userId);

        user.getFollowedUsers().add(followedUser.get());
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("{userId}/users/unfollow")
    public ResponseEntity<Void> unfollowUser(@PathVariable UUID userId, JwtAuthenticationToken jwtAuthenticationToken) {
        var user = userRepository.findById(UUID.fromString(jwtAuthenticationToken.getName())).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var followedUser = userRepository.findById(userId);

        user.getFollowedUsers().remove(followedUser.get());
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/followers")
    public ResponseEntity<List<UUID>> getFollowers(JwtAuthenticationToken jwtAuthenticationToken) {
        var user = userRepository.findById(UUID.fromString(jwtAuthenticationToken.getName())).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var followers = userRepository.findFollowedUserIds(user.getUserId());
        return ResponseEntity.ok(followers);
    }
}
