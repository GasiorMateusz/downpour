package com.cobra.repository.mongoDb;

import com.cobra.models.BasicMeasurements;
import com.cobra.repository.BasicMeasurementsDAO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoBasicMeasurementsDAO extends MongoRepository<BasicMeasurements, Long>, BasicMeasurementsDAO {
    @Override
    default BasicMeasurements update(BasicMeasurements basicMeasurements) {
        return save(basicMeasurements);
    }
    @Override
    default BasicMeasurements create(BasicMeasurements basicMeasurements) {
        return save(basicMeasurements);
    }
}
