package com.example.spring_weather_api.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.example.spring_weather_api.entity.WeatherEntity;
import com.example.spring_weather_api.resource.WeatherResource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WeatherJob implements Job {

  
  private WeatherResource weatherResource;

  public WeatherJob(WeatherResource weatherResource) {
    this.weatherResource = weatherResource;
  }

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    log.info("Starting weather job execution for city: Tartu");
    
    try {
      WeatherEntity weather = weatherResource.weather("Tartu");
      log.info("Weather job completed successfully. Weather: {}", weather.getMain());
    } catch (Exception e) {
      log.error("Failed to fetch weather data for city: Tartu", e);
      // Re-throw as JobExecutionException to let Quartz handle it
      throw new JobExecutionException("Weather job failed", e);
    }
  }
}
