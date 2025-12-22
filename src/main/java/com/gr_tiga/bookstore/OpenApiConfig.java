package com.gr_tiga.bookstore;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Book Store")
                        .version("1.0.0")
                        .description("""
                                Online Book Store API Documentation

                                - Rest API
                                - Swagger UI for Human Documentation
                                """));
    }

    // static final List<String> ENTITIES = List.of(
    // "admin", "book", "cart", "category", "customer");

    // @Bean
    // public List<GroupedOpenApi> entityGroups() {
    // return ENTITIES.stream()
    // .map(entity -> GroupedOpenApi.builder()
    // .group(StringUtils.capitalize(entity))
    // .pathsToMatch("/api" + entity + "/**")
    // .build()
    // ).toList();
    // }

    @Bean
    public GroupedOpenApi bookApi() {
        return GroupedOpenApi.builder()
                .group("Book")
                .pathsToMatch("/api/book/**")
                .build();
    }

    @Bean
    public GroupedOpenApi CartApi() {
        return GroupedOpenApi.builder()
                .group("Cart")
                .pathsToMatch("/api/cart/**")
                .build();
    }

    @Bean
    public GroupedOpenApi CategoryApi() {
        return GroupedOpenApi.builder()
                .group("Category")
                .pathsToMatch("/api/category/**")
                .build();
    }

    @Bean
    public GroupedOpenApi AdminApi() {
        return GroupedOpenApi.builder()
                .group("Admin")
                .pathsToMatch("/api/admin/**")
                .build();
    }

    @Bean
    public GroupedOpenApi CustomerApi() {
        return GroupedOpenApi.builder()
                .group("Customer")
                .pathsToMatch("/api/user/**")
                .build();
    }
}
