package com.gang.alphaspoon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

//http://localhost:8080/api/v1/swagger-ui.html
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gang.alphaspoon.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                ;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Alpha Spoon API",
                "Backend RESTful API for Alpha Spoon Web Application",
                "1.0",
                "https://xerathox.github.io/Alpha-Spoon/terms",
                new Contact("Alpha Spoon", "https://xerathox.github.io/Alpha-Spoon/", "apis@hampcode.com"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }
}
