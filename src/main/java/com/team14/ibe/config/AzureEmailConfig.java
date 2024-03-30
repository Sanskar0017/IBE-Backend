package com.team14.ibe.config;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureEmailConfig {

    @Value("${azure.communication.endpoint}")
    private String endpoint;

    @Value("${azure.communication.access-key}")
    private String accessKey;

    @Bean
    public EmailClient emailClient() {
        String connectionString = "endpoint=" + endpoint + ";accesskey=" + accessKey;
        return new EmailClientBuilder()
                .connectionString(connectionString)
                .buildClient();
    }
}
