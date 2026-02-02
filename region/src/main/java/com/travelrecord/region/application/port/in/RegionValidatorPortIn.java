package com.travelrecord.region.application.port.in;

import com.travelrecord.region.dto.RegionDto;

/**
 * Region 도메인이 제공하는 핵심 기능에 대한 Port (Input Port).
 * 이 인터페이스는 Region 모듈 외부에 노출되어 다른 서비스에서 Region 기능을 사용하기 위한 계약으로 사용됩니다.
 */
public interface RegionValidatorPortIn {

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
