package com.travelrecord.region.adapter.out;

import com.travelrecord.region.application.port.in.RegionValidatorPortIn;
import com.travelrecord.region.dto.RegionDto;
import com.travelrecord.region.service.RegionService;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Primary
public class RegionServiceAdapter implements RegionValidatorPortIn {

  private final RegionService regionService;

  // 1. 기존 에러 수정: 인터페이스의 정확한 메서드명 확인 (validateRegionCode로 추정)
  @Override
  public void validateRegionCode(String regionCode) {
    regionService.validateRegionCode(regionCode);
  }

  // 2. 누락된 메서드 추가
  @Override
  public  RegionDto.Response getRegionDetails(String regionCode) {
    // 우선은 서비스에서 상세 정보를 가져오도록 위임합니다.
    // Object 부분은 나중에 실제 상세 DTO 타입으로 바꾸시면 됩니다.
    return regionService.getRegionDetails(regionCode);
  }
}