package com.travelrecord.region.repository;

import com.travelrecord.region.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, String> {
    Optional<Region> findByRegionCode(String regionCode);
    boolean existsByRegionCode(String regionCode); // 유효성 검사를 위한 메서드
}