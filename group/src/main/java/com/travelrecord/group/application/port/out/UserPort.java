package com.travelrecord.group.application.port.out;

import com.travelrecord.group.dto.UserDto;

public interface UserPort {
    UserDto getUser(Long userId);
}
