package com.travelrecord.auth.security;

import com.travelrecord.auth.dto.AuthDto; // AuthDto.SignupRequest를 위해 임포트

/**
 * Auth 서비스가 User 서비스의 정보를 조회하기 위한 Port.
 */
public interface UserLookupPort {
    /**
     * User 서비스에 회원가입을 요청합니다.
     * @param request 회원가입 요청 정보
     */
    void requestSignup(AuthDto.SignupRequest request);

    /**
     * 이메일을 통해 사용자 상세 정보를 조회합니다. (인증에 필요한 정보만 포함)
     * @param email 조회할 사용자 이메일
     * @return 사용자 상세 정보 (ID, 이메일, 암호화된 비밀번호, 역할)
     */
    UserDetailResponse findUserDetailByEmail(String email);

    // 내부 전용 DTO
    class UserDetailResponse {
        private Long id;
        private String email;
        private String password; // 암호화된 비밀번호
        private String role; // 사용자 역할

        // Lombok getter/setter 또는 생성자
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }
}
