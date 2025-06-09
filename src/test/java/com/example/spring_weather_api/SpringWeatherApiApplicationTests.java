package com.example.spring_weather_api;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.spring_weather_api.domain.CityCordinates;
import com.example.spring_weather_api.entity.GeocodingCordinatesEntity;
import com.example.spring_weather_api.transformer.GeocordinatesTransformer;

@SpringBootTest
class SpringWeatherApiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testGeocordinatesTransformer() {
		final GeocordinatesTransformer geocodingCordinatesTransformer = new GeocordinatesTransformer();
		final GeocodingCordinatesEntity geocodingCordinatesEntity = new GeocodingCordinatesEntity();
		GeocodingCordinatesEntity.builder()
				.latitude("123")
				.longitude("345")
				.build();

		final CityCordinates cityCordinates = geocodingCordinatesTransformer.transformToDomain(geocodingCordinatesEntity);

		assertAll("Should return a city cordinates",
				() -> assertEquals(geocodingCordinatesEntity.getLatitude(), cityCordinates.getLatitude()),
				() -> assertEquals(geocodingCordinatesEntity.getLongitude(), cityCordinates.getLongitude()));

	}

}
