package com.cobra;

import com.cobra.exceptions.ConfigurationException;
import com.cobra.services.ImgwApi;
import com.cobra.services.WeatherDataService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class DownpourConfiguration {

    @Value("${configuration.weather.service}")
    private String weatherService;
    @Value("${db.name}")
    private String dbName;

    @Bean
    public WeatherDataService weatherDataService() {
        if("imgw".equals(weatherService)){
            return new ImgwApi();
        }else{
            throw new IllegalStateException("Invalid weather service configuration");
        }

    }

    @Bean
    public MongoDatabase mongoDatabase() throws ConfigurationException {
        String mongoClient = Optional.of(System.getenv("mongoClient").replaceAll("\"", ""))
                .orElseThrow(() -> new ConfigurationException("mangoClient is not set in the environment"));
        MongoClient client = MongoClients.create(mongoClient);
        return client.getDatabase(dbName);
    }

}
