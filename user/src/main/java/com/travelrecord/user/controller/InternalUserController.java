package com.travelrecord.user.controller;

import com.travelrecord.user.dto.AuthDto;
import com.travelrecord.user.dto.InternalUserDto;
import com.travelrecord.user.service.UserService;
import com.travelrecord.web.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Internal API", description = "인증 서비스용 User 내부 API (인증 필요)")
@RestController
@RequestMapping("/api/internal/users")
@RequiredArgsConstructor
public class InternalUserController {

    private final UserService userService;

    @Operation(summary = "내부 회원가입", description = "Auth 서비스로부터 회원가입 요청을 받아 User를 생성합니다. (Auth 서비스 내부 호출용)")
    @PostMapping("/signup")
    public ApiResponse<Void> signupInternal(@RequestBody AuthDto.SignupRequest request) {
        userService.signup(request);
        return ApiResponse.success(null);
    }

    @Operation(summary = "내부 사용자 정보 조회", description = "Auth 서비스로부터 사용자 이메일을 받아 상세 정보를 조회합니다. (Auth 서비스 내부 호출용)")
    @GetMapping("/by-email/{email}")
    public ApiResponse<InternalUserDto> getUserByEmailInternal(@PathVariable String email) {
        return ApiResponse.success(new InternalUserDto(userService.findByEmail(email)));
    }
}
