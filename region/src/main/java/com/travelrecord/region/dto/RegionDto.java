package com.travelrecord.region.dto;

import com.travelrecord.region.domain.Region;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegionDto {

    @Getter
    public static class Response {
        private String regionCode;
        private String name;

        public Response(Region region) {
            this.regionCode = region.getRegionCode();
            this.name = region.getName();
        }
    }
}