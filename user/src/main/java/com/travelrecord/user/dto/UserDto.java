package com.travelrecord.user.dto;

import com.travelrecord.user.domain.User;
import com.travelrecord.user.domain.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {

    @Getter
    public static class UserResponse {
        private Long id;
        private String email;
        private UserRole role;

        public UserResponse(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.role = user.getRole();
        }
    }
}