package com.travelrecord.support.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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

        return new OpenAPI().info(info);
    }

    @Configuration
    @ConditionalOnProperty(name = "springdoc.jwt.enabled", havingValue = "true")
    public static class JwtSecurityConfig {

        @Bean
        public OpenAPI customizeOpenAPIForJWT(OpenAPI openApi) {
            final String securitySchemeName = "bearerAuth";

            SecurityRequirement securityRequirement = new SecurityRequirement().addList(securitySchemeName);
            Components components = new Components()
                    .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                            .name(securitySchemeName)
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT"));

            return openApi
                    .addSecurityItem(securityRequirement)
                    .components(components);
        }
    }
}
