package com.travelrecord.post.application.port.out;

import com.travelrecord.region.dto.RegionDto; // RegionDto는 region 모듈의 DTO를 사용

/**
 * Post 도메인이 외부 Region 서비스에 요청하기 위한 Port (Output Port).
 * Post 서비스가 필요한 Region 관련 기능을 추상화한다.
 */
public interface RegionValidatorPortOut {

    /**
     * 특정 regionCode가 유효한지 검증합니다. 유효하지 않으면 예외를 발생시킵니다.
     * @param code 검증할 지역 코드
     */
    void validateRegionCode(String code);

    /**
     * 특정 regionCode에 해당하는 지역의 상세 정보를 조회합니다.
     * @param code 조회할 지역 코드
     * @return 지역 상세 정보
     */
    RegionDto.Response getRegionDetails(String code);
}
