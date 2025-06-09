package com.example.spring_weather_api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherEntity {
  @JsonProperty("id")
  private int id;
  @JsonProperty("main")
  private String main;
  @JsonProperty("description")
  private String description;
  @JsonProperty("icon")
  private String icon;
}
