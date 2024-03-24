package com.cobra.controllers;

import com.cobra.models.BasicMeasurements;
import com.cobra.services.WeatherDataService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/BasicMeasurements")
public class BasicMeasurementsController {

    private final WeatherDataService weatherDataService;
    Gson gson = new Gson();

    public BasicMeasurementsController(WeatherDataService weatherDataService) {
        this.weatherDataService = weatherDataService;
    }

    @GetMapping("/location/{station}")
    public String getBasicMeasurementsForStation(@PathVariable String station){
        log.info("Basic measurements requested for station: " + station);
        BasicMeasurements basicMeasurements = weatherDataService.getBasicMeasurementsForCity(station);
        return gson.toJson(basicMeasurements);
    }

    @GetMapping("/location")
    public String getBasicMeasurementsForAllStations(){
        log.info("Basic measurements requested for ALL stations ");
        List<BasicMeasurements> basicMeasurements = weatherDataService.getBasicMeasurementsForAllCities();//TODO
        return gson.toJson(basicMeasurements);
    }

}
