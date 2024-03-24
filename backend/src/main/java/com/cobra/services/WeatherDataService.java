package com.cobra.services;

import com.cobra.models.BasicMeasurements;

import java.util.List;

public interface WeatherDataService {

    BasicMeasurements getBasicMeasurementsForCity(String city);

    List<BasicMeasurements> getBasicMeasurementsForAllCities();

    List<BasicMeasurements> getBasicMeasurementsRainfallGreaterThan(float amount);
}
