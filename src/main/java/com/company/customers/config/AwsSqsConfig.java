package com.company.customers.config;


//import io.awspring.cloud.sqs.operations.SqsTemplate;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.sqs.SqsAsyncClient;

//@Configuration
public class AwsSqsConfig {

//    private final String accessKeyId;
//    private final String secretAccessKey;
//    private final String regionId;
//
//    public AwsSqsConfig(@Value("${aws.accessKeyId}") String accessKeyId,
//                        @Value("${aws.secretAccessKey}") String secretAccessKey,
//                        @Value("${aws.region}") String regionId) {
//        this.regionId = regionId;
//        this.accessKeyId = accessKeyId;
//        this.secretAccessKey = secretAccessKey;
//    }
//
//    @Bean
//    public SqsAsyncClient sqsAsyncClient() {
//        return SqsAsyncClient.builder()
//                .region(Region.of(regionId))
//                .credentialsProvider(StaticCredentialsProvider.create(
//                        AwsBasicCredentials.create(accessKeyId, secretAccessKey)))
//                .build();
//    }
//
//    @Bean
//    public SqsTemplate sqsTemplate(SqsAsyncClient sqsAsyncClient) {
//        return SqsTemplate.builder()
//                .sqsAsyncClient(sqsAsyncClient)
//                .build();
//    }
}
