package com.travelrecord.region;

import com.travelrecord.common.persistence.JpaAuditingConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(JpaAuditingConfig.class)
@SpringBootApplication(scanBasePackages = "com.travelrecord")
public class RegionApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegionApplication.class, args);
    }
}
