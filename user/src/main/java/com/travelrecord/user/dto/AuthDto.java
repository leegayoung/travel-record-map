package com.travelrecord.user.dto;

import lombok.Getter;
import lombok.Setter;

// Auth 서비스가 User 서비스의 내부 API를 호출할 때 사용하는 DTO
@Getter @Setter
public class AuthDto {

    @Getter @Setter
    public static class SignupRequest {
        private String email;
        private String password;
    }
}
