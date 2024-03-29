package com.cobra.repository.mongoDb;

import com.cobra.models.BasicMeasurements;
import com.cobra.repository.BasicMeasurementsDAO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoDao extends BasicMeasurementsDAO, MongoRepository<BasicMeasurements, String> {


    default List<BasicMeasurements> save(List<BasicMeasurements> basicMeasurements) {
        return saveAll(basicMeasurements);
    }
}
