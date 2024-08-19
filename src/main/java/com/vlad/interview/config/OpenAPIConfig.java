package com.vlad.interview.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {
    @Value("${application.openapi.dev-url}")
    private String devUrl;

    @Value("${application.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI openAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        devServer.setUrl(prodUrl);
        devServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setName("Vladyslav");
        contact.setUrl("https://www.linkedin.com/in/vladyslav-86b99693/");

        Info info = new Info()
                .title("Interview App ")
                .version("0.0.1-SNAPSHOT")
                .contact(contact)
                .description("This API exposes endpoints to manage users.");

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
