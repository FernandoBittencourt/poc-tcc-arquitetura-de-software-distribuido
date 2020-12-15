package com.github.fernandobittencourt.consultoria.configuration;

import org.springframework.beans.factory.annotation.Value;

//@Configuration
public class AwsSqsConfiguration {

    @Value("${aws.sqs.endpoint}")
    private String url;

    @Value("${aws.sqs.region}")
    private String region;

    @Value("${aws.access.key}")
    private String awsAccessKey;

    @Value("${aws.secret.key}")
    private String awsSecretKey;

   /* @Bean
    public QueueMessagingTemplate queueMessagingTemplate() {
        return new QueueMessagingTemplate(amazonSQSAsync());
    }

    public AmazonSQSAsync amazonSQSAsync() {
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
        return AmazonSQSAsyncClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .build();
    }*/
}
