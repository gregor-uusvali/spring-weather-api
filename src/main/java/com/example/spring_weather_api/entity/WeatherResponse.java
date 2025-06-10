package com.example.spring_weather_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherResponse(
  @JsonProperty("weather")
  WeatherEntity[] weather
) {
}
