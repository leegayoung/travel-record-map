package com.travelrecord.common.security.service;

import com.travelrecord.common.security.dto.UserDetailsDto;

public interface UserDetailsLoader {
    UserDetailsDto loadUserByUserId(Long userId);
}
