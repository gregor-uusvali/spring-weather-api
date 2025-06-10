package com.example.spring_weather_api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherEntity(
  @JsonProperty("id")
  int id,
  @JsonProperty("main")
  String main,
  @JsonProperty("description")
  String description,
  @JsonProperty("icon")
  String icon
) {
}
