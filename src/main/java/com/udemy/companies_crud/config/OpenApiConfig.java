package com.udemy.companies_crud.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Companies CRUD API",
                version = "1.0.0",
                description = "API for managing companies and their websites"
        ),
        servers = @Server(url = "http://localhost:8080", description = "Local server")
)
public class OpenApiConfig {
}
