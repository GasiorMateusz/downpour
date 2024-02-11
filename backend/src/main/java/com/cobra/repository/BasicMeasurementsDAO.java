package com.cobra.repository;

import com.cobra.models.BasicMeasurements;

import java.util.Optional;

public interface BasicMeasurementsDAO {
    BasicMeasurements create(BasicMeasurements basicMeasurements);
    Optional<BasicMeasurements> findById(Long id);
    BasicMeasurements update(BasicMeasurements basicMeasurements);
    void deleteByStationName(String stationName);
}
