package com.example.elisaappbe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins(
                                "http://localhost:5173",  // cho front-end (ReactJs)
                                "http://10.0.2.2:8080",  //  cho Android Emulator
                                "http://127.0.0.1:8080",  // cho iOS Simulator
                                "http://localhost:8080"
                        )
                        .allowedMethods("*");
            }
        };
    }
}