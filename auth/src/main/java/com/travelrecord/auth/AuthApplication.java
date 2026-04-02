package com.travelrecord.auth;

import com.travelrecord.common.persistence.JpaAuditingConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {
  "com.travelrecord.auth",
  "com.travelrecord.common.kafka",
  "com.travelrecord.common.persistence" // QuerydslConfig 스캔
})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
