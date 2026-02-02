package com.travelrecord.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthDto {

    @Getter @Setter
    public static class LoginRequest {
        private String email;
        private String password;
    }

    @Getter @Setter
    public static class SignupRequest {
        private String email;
        private String password;
    }

    @Getter @Setter
    public static class TokenResponse {
        private final String accessToken;

        public TokenResponse(String accessToken) {
            this.accessToken = accessToken;
        }
    }
}