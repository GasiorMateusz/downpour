package com.cobra.configuration;

import com.cobra.services.ImgwApi;
import com.cobra.services.WeatherDataService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ImgwConfiguration {

    @Primary
    @Bean
    @ConditionalOnProperty(name = "configuration.weather.service", havingValue = "imgw")
    public WeatherDataService weatherDataService() {
        return new ImgwApi();
    }
}

