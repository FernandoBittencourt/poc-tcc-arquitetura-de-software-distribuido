package com.github.fernandobittencourt.consultoria.service.integration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.github.fernandobittencourt.consultoria.service.IQueueService;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

public class QueueService implements IQueueService {

    @Value("${aws.sqs.url}")
    private String url;

    @Value("${aws.sqs.region}")
    private String region;

    @Value("${aws.access.key}")
    private String awsAccessKey;

    @Value("${aws.secret.key}")
    private String awsSecretKey;


    public void inserirMensagem(String mensagem){
        AmazonSQS amazonSqs = criarClienteSqs();
        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
        messageAttributes.put("AttributeOne", new MessageAttributeValue()
                .withStringValue("This is an attribute")
                .withDataType("String"));

        SendMessageRequest sendMessageStandardQueue = new SendMessageRequest()
                .withQueueUrl(url)
                .withMessageBody(mensagem)
                .withDelaySeconds(30)
                .withMessageAttributes(messageAttributes);

        amazonSqs.sendMessage(sendMessageStandardQueue);
    }


    private AmazonSQS criarClienteSqs() {
        AWSCredentials credentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
        Regions sqsRegion = Regions.fromName(region);
        return AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(sqsRegion)
                .build();
    }
}
