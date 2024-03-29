package com.cobra.repository;

import com.cobra.models.BasicMeasurements;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasicMeasurementsDAO {
    List<BasicMeasurements> save(List<BasicMeasurements> basicMeasurements);

    Optional<BasicMeasurements> findByStationName(String measurementsId);

    Optional<BasicMeasurements> findById(String measurementsId);

}
