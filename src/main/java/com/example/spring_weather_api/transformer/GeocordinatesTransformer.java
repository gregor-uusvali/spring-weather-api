package com.example.spring_weather_api.transformer;

import org.springframework.stereotype.Service;

import com.example.spring_weather_api.domain.CityCordinates;
import com.example.spring_weather_api.entity.GeocodingCordinatesEntity;

@Service
public class GeocordinatesTransformer {

  public CityCordinates transformToDomain(final GeocodingCordinatesEntity entity) {
    return CityCordinates.builder()
        .latitude(entity.getLatitude())
        .longitude(entity.getLongitude())
        .build();
  }
}
