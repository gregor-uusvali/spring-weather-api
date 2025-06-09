package com.example.spring_weather_api.service;

import org.springframework.stereotype.Service;

import com.example.spring_weather_api.domain.CityCordinates;
import com.example.spring_weather_api.domain.CityWeather;
import com.example.spring_weather_api.domain.WeatherRequestDetails;
import com.example.spring_weather_api.entity.WeatherResponse;
import com.example.spring_weather_api.provider.GeocodingProvider;
import com.example.spring_weather_api.provider.WeatherProvider;
import com.example.spring_weather_api.transformer.GeocordinatesTransformer;
import com.example.spring_weather_api.transformer.OpenWeatherTransformer;

@Service
public class WeatherService {

  private GeocodingProvider geocodingProvider;
  private GeocordinatesTransformer geocordinatesTransformer;
  private WeatherProvider weatherProvider;
  private OpenWeatherTransformer openWeatherTransformer;

  public WeatherService(GeocodingProvider geocodingProvider, GeocordinatesTransformer geocordinatesTransformer,
      WeatherProvider weatherProvider, OpenWeatherTransformer openWeatherTransformer) {
    this.geocodingProvider = geocodingProvider;
    this.geocordinatesTransformer = geocordinatesTransformer;
    this.weatherProvider = weatherProvider;
    this.openWeatherTransformer = openWeatherTransformer;
  }

  public WeatherResponse getWeather(final WeatherRequestDetails weatherRequestDetails) throws Exception {
    final CityCordinates cityCordinates = geocordinatesTransformer
        .transformToDomain(geocodingProvider.getCityCordinates(weatherRequestDetails));

    final CityWeather cityWeather = openWeatherTransformer
        .transformToDomain(weatherProvider.getCityWeather(cityCordinates));

    return openWeatherTransformer.transformToEntity(cityWeather);
  }

}
