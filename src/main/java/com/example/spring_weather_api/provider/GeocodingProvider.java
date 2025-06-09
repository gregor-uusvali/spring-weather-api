package com.example.spring_weather_api.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.spring_weather_api.config.AppConfig;
import com.example.spring_weather_api.domain.WeatherRequestDetails;
import com.example.spring_weather_api.entity.GeocodingCordinatesEntity;

@Service
public class GeocodingProvider {

  @Value("${geocoding.url}")
  private String geocodingUrl;

  private String apiKey;

  public GeocodingProvider(AppConfig appConfig) {
    this.apiKey = appConfig.getApiKey();
  }

  public GeocodingCordinatesEntity getCityCordinates(final WeatherRequestDetails weatherRequestDetails)
      throws RestClientException {
    final RestTemplate response = new RestTemplate();
    final ResponseEntity<GeocodingCordinatesEntity[]> responseEntity;
    HttpEntity<String> requestEntity = new HttpEntity<>(null, null);

    UriComponents urlBuilder = UriComponentsBuilder.fromHttpUrl(geocodingUrl)
        .queryParam("q", weatherRequestDetails.getCity())
        .queryParam("limit", 1)
        .queryParam("appid", apiKey)
        .build();

    try {
      responseEntity = response.exchange(urlBuilder.toUriString(), HttpMethod.GET, requestEntity,
          GeocodingCordinatesEntity[].class);
    } catch (RestClientException e) {
      throw new RuntimeException("Error getting city cordinates", e);
    }

    return responseEntity.getBody()[0];
  }

}
