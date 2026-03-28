package com.travelrecord.post;

import com.travelrecord.persistence.common.JpaAuditingConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(JpaAuditingConfig.class)
@SpringBootApplication(scanBasePackages = { "com.travelrecord.common.security"})
public class PostApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostApplication.class, args);
    }
}
