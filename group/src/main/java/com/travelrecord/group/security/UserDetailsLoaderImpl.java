package com.travelrecord.group.security;

import com.travelrecord.common.security.dto.UserDetailsDto;
import com.travelrecord.common.security.service.UserDetailsLoader;
import com.travelrecord.group.adapter.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsLoaderImpl implements UserDetailsLoader {

    private final UserClient userClient;

    @Override
    public UserDetailsDto loadUserByUserId(Long userId) {
        return userClient.getUserDetails(userId);
    }
}
