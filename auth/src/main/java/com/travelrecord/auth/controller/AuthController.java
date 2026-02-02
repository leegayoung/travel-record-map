package com.travelrecord.auth.controller;

import com.travelrecord.auth.dto.AuthDto;
import com.travelrecord.auth.service.AuthService;
import com.travelrecord.web.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "인증 관련 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "회원가입", description = "User 서비스에 회원가입을 요청합니다.")
    @PostMapping("/signup")
    public ApiResponse<Void> signup(@Valid @RequestBody AuthDto.SignupRequest request) {
        authService.signup(request);
        return ApiResponse.success(null);
    }

    @Operation(summary = "로그인", description = "사용자 이메일과 비밀번호로 로그인하고 JWT 토큰을 발급받습니다.")
    @PostMapping("/login")
    public ApiResponse<AuthDto.TokenResponse> login(@Valid @RequestBody AuthDto.LoginRequest request) {
        return ApiResponse.success(authService.login(request));
    }
}