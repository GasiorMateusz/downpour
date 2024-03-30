package com.cobra.controllers;

import com.cobra.models.BasicMeasurements;
import com.cobra.repository.BasicMeasurementsDAO;
import com.cobra.services.WeatherDataService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/BasicMeasurements")
public class BasicMeasurementsController {

    private final WeatherDataService weatherDataService;
    private final BasicMeasurementsDAO basicMeasurementsDAO;
    Gson gson = new Gson();

    public BasicMeasurementsController(WeatherDataService weatherDataService, BasicMeasurementsDAO basicMeasurementsDAO) {
        this.weatherDataService = weatherDataService;
        this.basicMeasurementsDAO = basicMeasurementsDAO;
    }

    @GetMapping("/location/{station}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String requestBasicMeasurementsForStation(@PathVariable String station) {
        log.info("Basic measurements requested for station: " + station);
        BasicMeasurements basicMeasurements = weatherDataService.getBasicMeasurementsForCity(station);
        return gson.toJson(basicMeasurements);
    }

    @GetMapping("/location")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String requestBasicMeasurementsForAllStations() {
        log.info("Basic measurements requested for ALL stations ");
        List<BasicMeasurements> basicMeasurements = weatherDataService.getBasicMeasurementsForAllCities();
        return gson.toJson(basicMeasurements);
    }

    @GetMapping("/location/rainfall/{amount}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String requestBasicMeasurementsRainfallGreaterThan(@PathVariable String amount) {
        log.info("Requested basic measurements with rainfall greater than " + amount);
        List<BasicMeasurements> basicMeasurements = weatherDataService.getBasicMeasurementsRainfallGreaterThan(
                Float.parseFloat(amount));
        return gson.toJson(basicMeasurements);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('MODERATOR')")
    public List<BasicMeasurements> saveBasicMeasurements(@RequestBody List<BasicMeasurements> measurements) {

        log.info("Saving  %s records to db".formatted(measurements.size()));
        basicMeasurementsDAO.save(measurements);
        return measurements;
    }

    @GetMapping("/stationName/{stationName}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Optional<BasicMeasurements> getBasicMeasurementsByStationName(@PathVariable String stationName) {
        Optional<BasicMeasurements> foundMeasurements = basicMeasurementsDAO.findByStationName(stationName);
        log.info("Get measurements for station name: %s ".formatted(stationName));
        log.debug(foundMeasurements.toString());
        return foundMeasurements;
    }

    @GetMapping("/id/{measurementsId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Optional<BasicMeasurements> getBasicMeasurementsById(@PathVariable String measurementsId) {
        Optional<BasicMeasurements> foundMeasurements = basicMeasurementsDAO.findById(measurementsId);
        log.info("Get measurements for id: %s ".formatted(measurementsId));
        log.debug(foundMeasurements.toString());
        return foundMeasurements;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/id/{measurementsId}")
    public void deleteBasicMeasurementsById(@PathVariable String measurementsId) {
        basicMeasurementsDAO.deleteById(measurementsId);
        log.info("deleted measurements for id: %s ".formatted(measurementsId));
    }

}
