package com.github.fernandobittencourt.consultoria.service.integration;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.github.fernandobittencourt.consultoria.service.IStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
    public String armazenarArquivo(MultipartFile arquivo) {
        try {
            AmazonS3 amazonS3 = criarClienteS3();
            String fileName = arquivo.getName();
            logger.info("O arquivo '"+fileName+"' será inserido no bucket '" + bucketName+"'");
            amazonS3.putObject(new PutObjectRequest(bucketName,
                    arquivo.getOriginalFilename(), arquivo.getInputStream(),null)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            return "http://s3.amazonaws.com/"+bucketName+"/"+arquivo.getOriginalFilename();

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
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException();
        }
    }

    private AmazonS3 criarClienteS3() {
        AWSCredentials credentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
        Regions s3Region = Regions.fromName(region);
        return AmazonS3ClientBuilder.standard()
                .withRegion(s3Region)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
}
