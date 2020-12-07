package com.github.fernandobittencourt.consultoria.service.integration;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.github.fernandobittencourt.consultoria.service.IStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class StorageService implements IStorageService {

    private static final Logger logger = LoggerFactory.getLogger(StorageService.class);

    @Value("${aws.s3.region}")
    private String region;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.access.key}")
    private String awsAccessKey;

    @Value("${aws.secret.key}")
    private String awsSecretKey;

    @Override
    public void armazenarArquivo(File arquivo) {
        AWSCredentials credentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
        Regions clientRegion = Regions.fromName(region);
        String fileName = arquivo.getName();

        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(clientRegion)
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .build();

            logger.info("O arquivo '"+fileName+"' será inserido no bucket '" + bucketName+"'");
            s3Client.putObject(bucketName, fileName, arquivo);

        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            logger.error(e.getMessage());
            throw new RuntimeException();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            logger.error(e.getMessage());
            throw new RuntimeException();
        }
    }
}
