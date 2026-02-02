package com.travelrecord.post.port.out;

/**
 * Post 도메인에서 외부 Region 도메인의 유효성을 검증하기 위한 Outgoing Port.
 */
public interface RegionValidatorPort {

    /**
     * regionCode가 유효한지 검증한다. 존재하지 않을 경우 예외가 발생해야 한다.
     * @param regionCode 검증할 지역 코드
     */
    void validateRegionExists(String regionCode);
}
