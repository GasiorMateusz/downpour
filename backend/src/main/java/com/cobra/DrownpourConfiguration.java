package com.cobra;

import com.cobra.services.ImgwApi;
import com.cobra.services.WeatherDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DrownpourConfiguration {

    @Value("${configuration.weather.service}")
    private String weatherService;

    @Bean
    public WeatherDataService weatherDataService() {
        if("imgw".equals(weatherService)){
            return new ImgwApi();
        }else{
            throw new IllegalStateException("Invalid weather service configuration");
        }

    }

}
