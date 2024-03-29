package com.cobra.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImgwConfiguration {

    @Value("${configuration.weather.service}")
    private String weatherService;

}
