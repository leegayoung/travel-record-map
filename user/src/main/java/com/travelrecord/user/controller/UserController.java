package com.travelrecord.user.controller;

import com.travelrecord.user.dto.UserDto;
import com.travelrecord.user.service.UserService;
import com.travelrecord.web.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "사용자 관련 API (인증 필요)")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth") // 이 컨트롤러의 모든 API에 JWT 인증 필요
public class UserController {

    private final UserService userService;

    @Operation(summary = "내 정보 조회", description = "현재 로그인한 사용자의 정보를 조회합니다.")
    @GetMapping("/{userId}")
    public ApiResponse<UserDto.UserResponse> getUserInfo(@PathVariable Long userId) {
        return ApiResponse.success(new UserDto.UserResponse(userService.findById(userId)));
    }
}