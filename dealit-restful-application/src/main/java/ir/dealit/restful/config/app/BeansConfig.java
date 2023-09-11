package ir.dealit.restful.config.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class BeansConfig {

    @Value("${arvan.s3.access_key}")
    private String accessKey;

    @Value("${arvan.s3.secret_key}")
    private String secretKey;

    @Value("${arvan.s3.endpoint}")
    private String endpoint;


    @Bean
    public S3Client s3Client() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
        return S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .endpointOverride(URI.create(endpoint))
                .region(Region.of("ir-thr-at1"))
                .build();
    }

}
