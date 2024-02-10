package com.cobra.controllers;

import com.cobra.models.BasicMeasurements;
import com.cobra.services.WeatherDataService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/BasicMeasurements")
public class BasicMeasurementsController {

    private final WeatherDataService weatherDataService;
    Gson gson = new Gson();

    public BasicMeasurementsController(WeatherDataService weatherDataService) {
        this.weatherDataService = weatherDataService;
    }

    @GetMapping("/{city}")
    public String getBasicMeasurementsForCity(@PathVariable String city){
        log.info("Basic measurements requested for city " + city);
        BasicMeasurements basicMeasurements = weatherDataService.getBasicMeasurements(city);
        return gson.toJson(basicMeasurements);
    }

}
