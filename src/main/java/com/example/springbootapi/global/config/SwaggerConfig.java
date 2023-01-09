package com.example.springbootapi.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(
        title = "잇서요 api 명세서",
        description = "잇서요 api ",
        version = "v1"
))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi itseoyoApi() {
        String [] paths = {"/api/v1/**"};

        return GroupedOpenApi
                .builder()
                .group("잇서요 api")
                .pathsToMatch(paths)
                .build();
    }
}
