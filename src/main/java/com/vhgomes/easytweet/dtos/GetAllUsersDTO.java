package com.vhgomes.easytweet.dtos;

import com.vhgomes.easytweet.models.Role;

import java.util.List;
import java.util.UUID;

public record GetAllUsersDTO(UUID id, String username, Role role, List<String> followersId) {
}
