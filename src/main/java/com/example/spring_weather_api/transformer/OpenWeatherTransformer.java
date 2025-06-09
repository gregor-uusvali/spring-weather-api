package com.example.spring_weather_api.transformer;

import org.springframework.stereotype.Service;

import com.example.spring_weather_api.domain.CityWeather;
import com.example.spring_weather_api.entity.OpenWeatherResponseEntity;
import com.example.spring_weather_api.entity.WeatherResponse;

@Service
public class OpenWeatherTransformer {

  public CityWeather transformToDomain(final OpenWeatherResponseEntity entity) {
    return CityWeather.builder()
      .weather(entity.getWeather()[0].getMain())
      .details(entity.getWeather()[0].getDescription())
      .build();
  }

  public WeatherResponse transformToEntity(final CityWeather domain) {
    return WeatherResponse.builder()
        .weather(domain.getWeather())
        .details(domain.getDetails())
        .build();
  }

}
