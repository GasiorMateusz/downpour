package com.cobra.configuration;

import com.cobra.services.ImgwApi;
import com.cobra.services.WeatherDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DownpourConfiguration {

    @Value("${configuration.weather.service}")
    private String weatherService;


    @Bean
    public WeatherDataService weatherDataService() {
        switch (weatherService) {
            case "imgw":
                return new ImgwApi();
            default:
                throw new IllegalStateException("Invalid weather service configuration" + weatherService);
        }
    }

}
