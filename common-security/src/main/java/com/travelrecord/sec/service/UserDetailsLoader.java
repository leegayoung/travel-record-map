package com.travelrecord.sec.service;

import com.travelrecord.sec.dto.UserDetailsDto;

public interface UserDetailsLoader {
    UserDetailsDto loadUserByUserId(Long userId);
}
