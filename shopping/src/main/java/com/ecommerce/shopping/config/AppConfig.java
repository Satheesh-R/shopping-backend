package com.ecommerce.shopping.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Value("${allowed.origins}")
    private String[] allowedOrigins;
    @Value("${spring.data.rest.base-path}")
    private String basePath;

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        //set up cors mapping
        corsRegistry.addMapping(basePath+"/**").allowedOrigins(allowedOrigins);
    }
}
