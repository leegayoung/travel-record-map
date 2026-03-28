package com.travelrecord.user.config;

import com.travelrecord.common.security.service.UserDetailsLoader;
import com.travelrecord.common.security.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Bean
    public JwtTokenProvider jwtTokenProvider(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.access-token-validity-in-seconds}") long validity,
            UserDetailsLoader userDetailsLoader
    ) {
        return new JwtTokenProvider(secretKey, validity, userDetailsLoader);
    }
}
