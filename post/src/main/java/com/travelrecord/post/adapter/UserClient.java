package com.travelrecord.post.adapter;

import com.travelrecord.common.security.dto.UserDetailsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/api/internal/users/{userId}")
    UserDetailsDto getUserDetails(@PathVariable("userId") Long userId);
}
