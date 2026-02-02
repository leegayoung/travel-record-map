package com.travelrecord.auth.port.out;

import com.travelrecord.auth.dto.AuthUserDto;

/**
 * Auth 도메인에서 외부 User 도메인의 정보를 가져오기 위한 Outgoing Port.
 */
public interface UserLoaderPort {

    /**
     * 이메일로 인증에 필요한 사용자 정보를 조회한다.
     * @param email 조회할 사용자 이메일
     * @return AuthUserDto 인증에 필요한 최소한의 사용자 정보
     */
    AuthUserDto loadUserByEmail(String email);
}
