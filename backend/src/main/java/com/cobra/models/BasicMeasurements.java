package com.cobra.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class BasicMeasurements {
    private float rainfall;
    private Timestamp measureTime;
}
