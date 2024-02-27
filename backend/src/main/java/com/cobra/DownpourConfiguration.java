package com.cobra;

import com.cobra.services.ImgwApi;
import com.cobra.services.WeatherDataService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public MongoDatabase mongoDatabase(){
        MongoClient client = MongoClients.create(System.getenv("mongoClient"));
        return client.getDatabase(dbName);
    }

}
