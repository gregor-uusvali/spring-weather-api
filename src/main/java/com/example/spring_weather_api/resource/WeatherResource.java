package com.example.spring_weather_api.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_weather_api.entity.WeatherEntity;
import com.example.spring_weather_api.service.WeatherService;

@RestController
@RequestMapping("/api/v1")
public class WeatherResource {

  private final WeatherService weatherService;

  public WeatherResource(WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  @GetMapping("/weather/{city}")
  public @ResponseBody WeatherEntity weather(@PathVariable String city) throws Exception {
    WeatherEntity weather = weatherService.getWeather(city);
    System.out.println(weather);
    return weather;
  }
}
