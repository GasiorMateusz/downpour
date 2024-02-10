package com.cobra.services;

import com.cobra.models.BasicMeasurements;

public interface WeatherDataService {

    BasicMeasurements getBasicMeasurements(String city);

}
