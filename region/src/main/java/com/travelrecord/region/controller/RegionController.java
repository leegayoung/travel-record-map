package com.travelrecord.region.controller;

import com.travelrecord.region.application.port.in.RegionValidatorPortIn; // Import RegionValidatorPortIn
import com.travelrecord.region.dto.RegionDto;
import com.travelrecord.region.service.RegionService; // RegionService는 getAllRegions를 위해 유지
import com.travelrecord.web.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Region", description = "지역 관련 API (인증 불필요)")
@RestController
@RequestMapping("/api/regions")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService; // getAllRegions를 위해 유지
    private final RegionValidatorPortIn regionValidatorPortIn; // RegionValidatorPortIn 주입

    @Operation(summary = "모든 지역 조회", description = "모든 한국 시/도/군 지역 정보를 조회합니다.")
    @GetMapping
    public ApiResponse<List<RegionDto.Response>> getAllRegions() {
        return ApiResponse.success(regionService.getAllRegions());
    }

    @Operation(summary = "단일 지역 조회", description = "특정 지역 코드를 가진 지역 정보를 조회합니다.")
    @GetMapping("/{regionCode}")
    public ApiResponse<RegionDto.Response> getRegionDetails(@PathVariable String regionCode) {
        return ApiResponse.success(regionValidatorPortIn.getRegionDetails(regionCode));
    }

    @Operation(summary = "지역 존재 여부 확인", description = "특정 지역 코드가 존재하는지 확인합니다. (Post 서비스에서 호출)")
    @GetMapping("/{regionCode}/exists")
    public ApiResponse<Boolean> validateRegionCode(@PathVariable String regionCode) {
        regionValidatorPortIn.validateRegionCode(regionCode); // 유효성 검증 (예외 발생 시 404)
        return ApiResponse.success(true); // 예외가 발생하지 않으면 true 반환
    }
}