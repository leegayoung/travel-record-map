package com.travelrecord.region.domain;

import com.travelrecord.persistence.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "region")
public class Region extends BaseEntity {

    @Id
    @Column(name = "region_code")
    private String regionCode;

    @Column(nullable = false)
    private String name;

    @Builder
    public Region(String regionCode, String name) {
        this.regionCode = regionCode;
        this.name = name;
    }
}