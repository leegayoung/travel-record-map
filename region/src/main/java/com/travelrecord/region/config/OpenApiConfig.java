package com.travelrecord.region.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(@Value("${spring.application.name}") String appName) {
        Info info = new Info()
                .title(appName + " API")
                .version("v1.0.0")
                .description(appName + " API 명세서");

        return new OpenAPI()
                .info(info);
    }
}
