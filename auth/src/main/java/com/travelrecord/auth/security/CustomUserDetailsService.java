package com.travelrecord.auth.security;

import com.travelrecord.auth.security.UserLookupPort.UserDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserLookupPort userLookupPort;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetailResponse userDetailResponse = userLookupPort.findUserDetailByEmail(email);

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userDetailResponse.getRole());
        return new org.springframework.security.core.userdetails.User(
                String.valueOf(userDetailResponse.getId()),
                userDetailResponse.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}
