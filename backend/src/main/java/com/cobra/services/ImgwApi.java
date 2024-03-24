package com.cobra.services;

import com.cobra.models.BasicMeasurements;
import com.cobra.utils.HttpClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ImgwApi implements WeatherDataService {
    public static final String GODZINA_POMIARU = "godzina_pomiaru";
    public static final String SUMA_OPADU = "suma_opadu";
    String baseUrl = "https://danepubliczne.imgw.pl/api/data/synop/station/";

    @Override
    public BasicMeasurements getBasicMeasurementsForCity(String city) {
        JsonObject response = Objects.requireNonNull(HttpClient.sendGetRequest(baseUrl + city)).getAsJsonObject();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = now.format(formatter);
        if (response == null || !response.has(GODZINA_POMIARU) || !response.has(SUMA_OPADU)) {
            throw new RuntimeException("No time or rainfall");
        }
        String dateTimeString = currentDate + "T" + response.get(GODZINA_POMIARU).getAsString() + ":00";
        LocalDateTime timestamp = LocalDateTime.parse(dateTimeString);
        return new BasicMeasurements(
                null,
                city,
                response.get(SUMA_OPADU).getAsFloat(),
                Timestamp.valueOf(timestamp));
    }

    @Override
    public List<BasicMeasurements> getBasicMeasurementsForAllCities() {
        return null;
    }
}
