package com.travelrecord.user.dto;

import com.travelrecord.user.domain.User;
import lombok.Getter;
import lombok.Setter;

// Auth 서비스가 User 서비스로부터 사용자 상세 정보를 조회할 때 사용하는 DTO
@Getter @Setter
public class InternalUserDto {
    private Long id;
    private String email;
    private String password; // 암호화된 비밀번호
    private String role; // 사용자 역할

    public InternalUserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole().name();
    }
}
