package com.cobra.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;


@Document
@Data
@AllArgsConstructor
public class BasicMeasurements {
    @Id
    private Long id;
    private String stationName;
    private float rainfall;
    private Timestamp measureTime;
}
