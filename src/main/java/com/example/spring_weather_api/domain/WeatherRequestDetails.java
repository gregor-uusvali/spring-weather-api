package com.example.spring_weather_api.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder 
public class WeatherRequestDetails {

  private String city;

}
