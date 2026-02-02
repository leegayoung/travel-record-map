package com.travelrecord.auth.service;

import com.travelrecord.auth.dto.AuthDto;
import com.travelrecord.auth.security.UserLookupPort; // UserLookupPort 임포트
import com.travelrecord.auth.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserLookupPort userLookupPort; // User 서비스와의 통신을 위한 Port

    @Transactional
    public void signup(AuthDto.SignupRequest request) {
        // User 서비스에 회원가입 요청 위임
        userLookupPort.requestSignup(request);
    }

    @Transactional
    public AuthDto.TokenResponse login(AuthDto.LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtTokenProvider.createToken(authentication);
        return new AuthDto.TokenResponse(accessToken);
    }
}