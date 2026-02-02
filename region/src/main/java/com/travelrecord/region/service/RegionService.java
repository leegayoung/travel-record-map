package com.travelrecord.region.service;

import com.travelrecord.region.domain.Region;
import com.travelrecord.region.dto.RegionDto;
import com.travelrecord.region.application.port.in.RegionValidatorPortIn; // Import RegionValidatorPortIn
import com.travelrecord.region.repository.RegionRepository;
import com.travelrecord.web.common.exception.*;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionService implements RegionValidatorPortIn { // implements RegionValidatorPortIn

    private final RegionRepository regionRepository;

    // 초기 데이터 설정을 위한 PostConstruct (테스트용)
    @PostConstruct
    public void init() {
        if (regionRepository.count() == 0) {
            List<Region> defaultRegions = Arrays.asList(
                    new Region("11", "서울특별시"),
                    new Region("28", "인천광역시"),
                    new Region("41", "경기도"),
                    new Region("44", "충청남도"),
                    new Region("50", "제주특별자치도")
            );
            regionRepository.saveAll(defaultRegions);
        }
    }

    // RegionValidatorPort 구현
    @Override
    public void validateRegionCode(String code) {
        if (!regionRepository.existsByRegionCode(code)) {
            throw new CustomException(ErrorCode.REGION_NOT_FOUND);
        }
    }

    // RegionValidatorPort 구현
    @Override
    public RegionDto.Response getRegionDetails(String code) {
        Region region = regionRepository.findByRegionCode(code)
                .orElseThrow(() -> new CustomException(ErrorCode.REGION_NOT_FOUND));
        return new RegionDto.Response(region);
    }

    public List<RegionDto.Response> getAllRegions() {
        return regionRepository.findAll().stream()
                .map(RegionDto.Response::new)
                .collect(Collectors.toList());
    }
}