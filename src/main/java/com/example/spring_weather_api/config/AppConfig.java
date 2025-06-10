package com.example.spring_weather_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class AppConfig {
    
    @Value("${weather.api.key}")
    private String apiKey;
} 