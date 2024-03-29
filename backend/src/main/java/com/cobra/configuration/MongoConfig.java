package com.cobra.configuration;

import com.cobra.exceptions.ConfigurationException;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@Slf4j
@EnableMongoRepositories(basePackages = "com.cobra.repository.mongoDb")
public class MongoConfig {
    @Value("${db.name}")
    private String dbName;
    @Value("${db.connectionString}")
    private String connectionString;

    @Primary
    @Bean
    @ConditionalOnProperty(name = "configuration.db", havingValue = "mongodb")
    public MongoTemplate mongoTemplate() throws ConfigurationException {
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(
                        connectionString.formatted(
                                System.getenv("MongoDBUsername"),
                                System.getenv("MongoDBUserpass"))))
                .serverApi(serverApi)
                .build();
        MongoClient client = MongoClients.create(settings);

        return new MongoTemplate(client, dbName);
    }
}
