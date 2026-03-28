package com.travelrecord.auth;

import com.travelrecord.persistence.common.JpaAuditingConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(JpaAuditingConfig.class)
@SpringBootApplication(scanBasePackages = {"com.travelrecord.auth", "com.travelrecord.common.kafka", "com.travelrecord.common.security"})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
