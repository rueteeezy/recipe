package com.recime.recipe.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI recipeApiOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Recipe Management API")
                        .description("REST API for managing cooking recipes")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Recipe BE Team")
                                .email("support@recime.app"))
                        .license(new License()
                                .name("Apache 2.0")));
    }

}
