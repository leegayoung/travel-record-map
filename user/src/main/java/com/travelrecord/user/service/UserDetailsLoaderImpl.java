package com.travelrecord.user.service;

import com.travelrecord.common.security.dto.UserDetailsDto;
import com.travelrecord.common.security.service.UserDetailsLoader;
import com.travelrecord.user.domain.User;
import com.travelrecord.user.repository.UserRepository;
import com.travelrecord.web.common.exception.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsLoaderImpl implements UserDetailsLoader {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetailsDto loadUserByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return UserDetailsDto.builder()
                .userId(user.getId())
                .role(user.getRole().name())
                .build();
    }
}
