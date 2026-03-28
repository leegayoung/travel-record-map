package com.travelrecord.post.security;

import com.travelrecord.common.security.dto.UserDetailsDto;
import com.travelrecord.common.security.service.UserDetailsLoader;
import com.travelrecord.post.adapter.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsLoaderImpl implements UserDetailsLoader {

    private final UserClient userClient;

    @Override
    public UserDetailsDto loadUserByUserId(Long userId) {
        return Optional.ofNullable(userClient.getUserDetails(userId))
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }
}
