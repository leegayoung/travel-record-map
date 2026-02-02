package com.travelrecord.user.service;

import com.travelrecord.user.domain.User;
import com.travelrecord.user.domain.UserRole;
import com.travelrecord.user.dto.AuthDto;
import com.travelrecord.user.dto.UserDto;
import com.travelrecord.user.repository.UserRepository;
import com.travelrecord.web.common.exception.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserDto.UserResponse signup(AuthDto.SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        User newUser = User.builder()
                .email(request.getEmail())
                .password(request.getPassword()) // 비밀번호는 이미 Auth 서비스에서 인코딩되어 넘어옴
                .role(UserRole.USER)
                .build();

        return new UserDto.UserResponse(userRepository.save(newUser));
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}