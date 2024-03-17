//package com.team14.IBE.config;
//
//import com.azure.storage.blob.BlobServiceClientBuilder;
//import com.azure.storage.blob.BlobServiceClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AzureStorageConfig {
//
//    @Value("${azure.storage.blob.connection-string}")
//    private String connectionString;
//
//    @Bean
//    public BlobServiceClient blobServiceClient() {
//        return new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
//    }
//}
