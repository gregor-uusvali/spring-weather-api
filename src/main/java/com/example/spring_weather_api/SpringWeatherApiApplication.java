package com.example.spring_weather_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.example.spring_weather_api.config.AppConfig;

@SpringBootApplication
public class SpringWeatherApiApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringWeatherApiApplication.class, args);
		AppConfig appConfig = context.getBean(AppConfig.class);
	}
}
