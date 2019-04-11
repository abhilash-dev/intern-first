package com.glassdoor.test.intern.first.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * Configuration class to enable Swagger API documentation & Swagger UI
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private static final Logger LOG = LoggerFactory.getLogger(SwaggerConfig.class);

    @Bean
    public Docket paymentAPI() {
        LOG.trace("Entering paymentAPI docket method");
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.glassdoor.test.intern.first"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfo(
                        "Payment API documentation",
                        "Payment API for an online payment processing system as described in Glassdoor backend intern take home assignment",
                        "1.0",
                        "",
                        new Contact("Abhilash Sulibela", "https://www.linkedin.com/in/abhilash-sulibela-5158576b/", "abhilash1869@gmail.com"),
                        "",
                        "",
                        Collections.emptyList()
                ));
    }
}