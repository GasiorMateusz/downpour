package com.cobra.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document
@Data
@AllArgsConstructor
public class BasicMeasurements {
    @Id
    public String id;
    private String stationName;
    private float rainfall;
    private Date measureTime;
}
