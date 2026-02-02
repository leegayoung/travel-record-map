package com.travelrecord.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthUserDto {
    private final Long id;
    private final String email;
    private final String password;
    private final String role;

    @Builder
    public AuthUserDto(Long id, String email, String password, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
