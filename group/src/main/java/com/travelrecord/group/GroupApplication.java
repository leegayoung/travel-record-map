package com.travelrecord.group;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(scanBasePackages = {
  "com.travelrecord.group",
  "com.travelrecord.common.kafka",
  "com.travelrecord.common.security",
  "com.travelrecord.common.persistence",
})
@EnableFeignClients(basePackages = "com.travelrecord.group.adapter") // UserClient 위치
public class GroupApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupApplication.class, args);
    }
}
