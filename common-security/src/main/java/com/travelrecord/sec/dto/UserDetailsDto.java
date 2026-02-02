package com.travelrecord.sec.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Collection;
import java.util.Collections;

@Getter
public class UserDetailsDto {

    private final Long userId;
    private final Collection<String> authorities;

    @Builder
    public UserDetailsDto(Long userId, String role) {
        this.userId = userId;
        this.authorities = Collections.singletonList(role);
    }
}
