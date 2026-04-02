package com.travelrecord.post;

import com.travelrecord.common.persistence.JpaAuditingConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;


@SpringBootApplication(scanBasePackages = {
  "com.travelrecord.post",
  "com.travelrecord.common.persistence",
  "com.travelrecord.common.security"
})
@EnableFeignClients(basePackages = "com.travelrecord.post.adapter") // UserClient 위치
public class PostApplication {
  public static void main(String[] args) {
    SpringApplication.run(PostApplication.class, args);
  }
}