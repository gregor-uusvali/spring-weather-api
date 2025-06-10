package com.example.spring_weather_api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.spring_weather_api.config.AppConfig;
import com.example.spring_weather_api.entity.CityCoordinates;
import com.example.spring_weather_api.entity.WeatherEntity;
import com.example.spring_weather_api.entity.WeatherResponse;

@Service
public class WeatherService {

  @Value("${geocoding.url}")
  private String geoCordUrl;

  @Value("${weather.url}")
  private String weatherUrl;

  private String apiKey;


  public WeatherService(AppConfig appConfig) {
    this.apiKey = appConfig.getApiKey();
  }

  public WeatherEntity getWeather(String city) {

    RestTemplate restTemplate = new RestTemplate();
    UriComponents urlBuilder = UriComponentsBuilder.fromUriString(geoCordUrl)
        .queryParam("q", city)
        .queryParam("limit", "1")
        .queryParam("appid", apiKey)
        .build();

    CityCoordinates[] cityCoordinates = restTemplate.getForObject(urlBuilder.toUriString(), CityCoordinates[].class);

    urlBuilder = UriComponentsBuilder.fromUriString(weatherUrl)
      .queryParam("lat", cityCoordinates[0].getLatitude())
      .queryParam("lon", cityCoordinates[0].getLongitude())
      .queryParam("appid", apiKey)
      .build();

    WeatherResponse weather = restTemplate.getForObject(urlBuilder.toUriString(), WeatherResponse.class);

    return weather.getWeather()[0];
  }

}
