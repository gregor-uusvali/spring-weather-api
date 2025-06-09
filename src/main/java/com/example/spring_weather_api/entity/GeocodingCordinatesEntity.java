package com.example.spring_weather_api.entity;

import lombok.Builder;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodingCordinatesEntity {
  @JsonProperty("lat")
  private String latitude;
  @JsonProperty("lon")
  private String longitude;
}
