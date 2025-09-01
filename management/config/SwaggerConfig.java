package com.hospital.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI hospitalOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Hospital Management System API")
            .version("1.0")
            .description("JWT-secured API with pagination, validation and scheduling rules"));
  }
}
