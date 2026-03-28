package com.travelrecord.user.controller;

import com.travelrecord.common.security.dto.UserDetailsDto;
import com.travelrecord.user.domain.User;
import com.travelrecord.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal/users")
@RequiredArgsConstructor
public class InternalUserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public UserDetailsDto getUserDetails(@PathVariable Long userId) {
        User user = userService.findById(userId);
        return UserDetailsDto.builder()
                .userId(user.getId())
                .username(user.getEmail())
                .role(user.getRole().name())
                .enabled(true)
                .build();
    }
}
