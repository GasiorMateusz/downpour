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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ImgwApi implements WeatherDataService {
    public static final String GODZINA_POMIARU = "godzina_pomiaru";
    public static final String SUMA_OPADU = "suma_opadu";
    public static final String STACJA = "stacja";
    public static final String NULL = "null";

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
        JsonArray response = Objects.requireNonNull(HttpClient.sendGetRequest(baseUrl)).getAsJsonArray();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = now.format(formatter);
        List<BasicMeasurements> cities = new ArrayList<>();
        for (JsonElement element : response
        ) {
            JsonObject city = element.getAsJsonObject();
            if (city == null || !city.has(GODZINA_POMIARU) || !city.has(SUMA_OPADU)) {
                throw new RuntimeException("No time or rainfall for "
                        + (city != null ? city.get(STACJA).getAsString() : NULL));
            }
            String dateTimeString = currentDate + "T" + city.get(GODZINA_POMIARU).getAsString() + ":00";
            LocalDateTime timestamp = LocalDateTime.parse(dateTimeString);
            cities.add(
                    new BasicMeasurements(
                            null,
                            city.get(STACJA).getAsString(),
                            city.get(SUMA_OPADU).getAsFloat(),
                            Timestamp.valueOf(timestamp))
            );
        }
        return cities;
    }
}
