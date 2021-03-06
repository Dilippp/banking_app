package com.nineleaps.banking.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = {"swagger.base.package.scan", "swagger.api.vendor.name"})
public class SwaggerConfig {

    @Value("${swagger.base.package.scan}")
    private String packageName;

    @Value("#{'${swagger.api.vendor.name}'.split(',')}")
    private List<String> vendorNames;

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(packageName))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        return new ApiInfo(
                "Swagger documentation for Banking API",
                "Banking's API for UI developers",
                "1.0",
                "Terms of Service",
                new Contact(
                        "Banking Service",
                        "https://www.nineleaps.com",
                        "dilip.chauhan@nineleaps.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html",
                Collections.emptyList());
    }
}
