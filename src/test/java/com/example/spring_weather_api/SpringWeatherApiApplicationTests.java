package com.example.spring_weather_api;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.spring_weather_api.domain.CityCordinates;
import com.example.spring_weather_api.domain.CityWeather;
import com.example.spring_weather_api.entity.GeocodingCordinatesEntity;
import com.example.spring_weather_api.entity.OpenWeatherResponseEntity;
import com.example.spring_weather_api.entity.WeatherEntity;
import com.example.spring_weather_api.entity.WeatherResponse;
import com.example.spring_weather_api.transformer.GeocordinatesTransformer;
import com.example.spring_weather_api.transformer.OpenWeatherTransformer;

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

	@Test
	void testOpenWeatherTransformer() {
		final WeatherEntity weather = new WeatherEntity();
		weather.setMain("Rain");
		weather.setDescription("moderate rain");

		final WeatherEntity[] weatherEntities = { weather };
		final OpenWeatherResponseEntity openWeatherResponseEntity = OpenWeatherResponseEntity.builder()
				.weather(weatherEntities)
				.build();

		final OpenWeatherTransformer openWeatherTransformer = new OpenWeatherTransformer();
		final CityWeather cityWeather = openWeatherTransformer.transformToDomain(openWeatherResponseEntity);

		assertAll("Should return a domain weather object",
				() -> assertEquals(openWeatherResponseEntity.getWeather()[0].getMain(), cityWeather.getWeather()),
				() -> assertEquals(openWeatherResponseEntity.getWeather()[0].getDescription(), cityWeather.getDetails()));

	}

	@Test
	void testShouldTransformCityWeatherToEntity() {
		final OpenWeatherTransformer openWeatherTransformer = new OpenWeatherTransformer();
		final CityWeather cityWeather = new CityWeather();
		cityWeather.setWeather("Rain");
		cityWeather.setDetails("moderate rain");

		final WeatherResponse weatherResponse = openWeatherTransformer.transformToEntity(cityWeather);

		assertAll("Should return a weather response",
				() -> assertEquals(cityWeather.getWeather(), weatherResponse.getWeather()),
				() -> assertEquals(cityWeather.getDetails(), weatherResponse.getDetails()));
	}
}
