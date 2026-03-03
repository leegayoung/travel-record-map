package com.travelrecord.auth.adapter;

import com.travelrecord.auth.dto.AuthDto;
import com.travelrecord.auth.security.UserLookupPort;
import com.travelrecord.web.common.ApiResponse; // ApiResponse 임포트
import com.travelrecord.web.common.exception.*;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class UserLookupAdapter implements UserLookupPort {

    private final RestTemplate restTemplate;

    @Value("${app.services.user.url}")
    private String userServiceUrl;

    @Override
    public void requestSignup(AuthDto.SignupRequest request) {
        String url = userServiceUrl + "/api/internal/users/signup";
        try {
            restTemplate.postForObject(url, request, String.class);
        } catch (HttpClientErrorException.Conflict e) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        } catch (HttpClientErrorException e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UserDetailResponse findUserDetailByEmail(String email) {
        String url = userServiceUrl + "/api/internal/users/by-email/" + email;
        try {

          ResponseEntity<ApiResponse<UserDetailResponse>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<ApiResponse<UserDetailResponse>>() {}
          );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null && response.getBody().getData() != null) {
                return response.getBody().getData();
            } else {
                throw new CustomException(ErrorCode.USER_NOT_FOUND);
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        } catch (HttpClientErrorException e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
