package com.example.spring_weather_api.entity;

import com.example.spring_weather_api.domain.CityWeather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {
  private String weather;
  private String details;
}
