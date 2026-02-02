package com.travelrecord.post.adapter;

import com.travelrecord.region.dto.RegionDto; // RegionDto는 region 모듈의 DTO를 사용
import com.travelrecord.post.application.port.out.RegionValidatorPortOut; // Post 모듈의 Outbound Port 임포트
import com.travelrecord.web.common.exception.*;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RegionApiAdapter implements RegionValidatorPortOut { // Post 모듈의 Outbound Port 구현

    private final RestTemplate restTemplate;

    @Value("${app.services.region.url}")
    private String regionServiceUrl;

    @Override
    public void validateRegionCode(String code) {
        String url = String.format("%s/api/regions/%s/exists", regionServiceUrl, code);
        try {
            ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
            if (!response.getStatusCode().is2xxSuccessful() || (response.getBody() == null || !response.getBody())) {
                throw new CustomException(ErrorCode.REGION_NOT_FOUND);
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new CustomException(ErrorCode.REGION_NOT_FOUND);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public RegionDto.Response getRegionDetails(String code) {
        String url = String.format("%s/api/regions/%s", regionServiceUrl, code);
        try {
            return restTemplate.getForObject(url, RegionDto.Response.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new CustomException(ErrorCode.REGION_NOT_FOUND);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
