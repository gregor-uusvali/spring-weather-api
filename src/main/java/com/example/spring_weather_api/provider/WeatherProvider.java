package com.example.spring_weather_api.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.spring_weather_api.config.AppConfig;
import com.example.spring_weather_api.domain.CityCordinates;
import com.example.spring_weather_api.domain.CityWeather;
import com.example.spring_weather_api.entity.OpenWeatherResponseEntity;

@Service
public class WeatherProvider {

  @Value("${weather.url}")
  private String weatherUrl;

  private String apiKey;

  public WeatherProvider(AppConfig appConfig) {
    this.apiKey = appConfig.getApiKey();
  }

  public OpenWeatherResponseEntity getCityWeather(final CityCordinates cityCordinates) throws Exception {
    final RestTemplate response = new RestTemplate();
    final ResponseEntity<OpenWeatherResponseEntity> responseEntity;
    HttpEntity<String> requestEntity = new HttpEntity<>(null, null);

    UriComponents urlBuilder = UriComponentsBuilder.fromHttpUrl(weatherUrl)
        .queryParam("lat", cityCordinates.getLatitude())
        .queryParam("lon", cityCordinates.getLongitude())
        .queryParam("appid", apiKey)
        .build();

    try {
      responseEntity = response.exchange(urlBuilder.toUriString(), HttpMethod.GET, requestEntity,
          OpenWeatherResponseEntity.class);
    } catch (HttpStatusCodeException e) {
      throw new Exception(e.getMessage());
    }

    return responseEntity.getBody();
  }

}
