package com.example.javawork.controller.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("*")
                .allowedOriginPatterns("*") // 或者可以列出具体的域，如 "http://localhost:8080"
                .allowedMethods("GET", "POST")
                .allowCredentials(true);
    }
}
