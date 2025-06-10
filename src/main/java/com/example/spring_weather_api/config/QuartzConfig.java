package com.example.spring_weather_api.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.spring_weather_api.jobs.WeatherJob;

@Configuration
public class QuartzConfig {
  @Bean
  JobDetail jobDetail() {
    return JobBuilder.newJob(WeatherJob.class)
        .withIdentity("weatherJob")
        .storeDurably()
        .build();
  }

  @Bean
  SimpleTrigger trigger(JobDetail jobDetail) {
    return TriggerBuilder.newTrigger()
        .forJob(jobDetail)
        .withIdentity("weatherTrigger")
        .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(10))
        .build();
  }
}
