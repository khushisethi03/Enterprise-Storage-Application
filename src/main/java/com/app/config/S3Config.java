package com.app.config;

import java.net.URI;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Autowired
    private StorageProperties storageProperties;

    @Bean
    public S3Client s3Client() {

        Objects.requireNonNull(
                storageProperties.getEndpoint(),
                "MinIO endpoint is missing");

        return S3Client.builder()
                .endpointOverride(
                        URI.create(
                                storageProperties.getEndpoint()))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        storageProperties.getAccessKey(),
                                        storageProperties.getSecretKey())))
                .region(
                        Region.of(
                                storageProperties.getRegion()))
                .forcePathStyle(true)
                .build();
    }
}