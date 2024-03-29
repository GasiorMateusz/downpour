package com.cobra.repository;

import com.cobra.models.BasicMeasurements;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasicMeasurementsDAO {
    BasicMeasurements create(BasicMeasurements basicMeasurements);

    BasicMeasurements update(BasicMeasurements basicMeasurements);

    void deleteByStationName(String stationName);

    Optional<BasicMeasurements> findByStationName(String measurementsId);

    Optional<BasicMeasurements> findById(String measurementsId);
}
