package com.example.spring_weather_api.resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_weather_api.domain.WeatherRequestDetails;
import com.example.spring_weather_api.entity.WeatherResponse;
import com.example.spring_weather_api.service.WeatherService;

@RestController
@RequestMapping("/api/v1")
public class WeatherResource {

  private final WeatherService weatherService;

  public WeatherResource(WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  @GetMapping("/weather/{city}")
  public @ResponseBody WeatherResponse weather(@PathVariable("city") String city) throws Exception {
    final WeatherRequestDetails weatherRequestDetails = WeatherRequestDetails.builder().city(city).build();
    return weatherService.getWeather(weatherRequestDetails);
  }

}
