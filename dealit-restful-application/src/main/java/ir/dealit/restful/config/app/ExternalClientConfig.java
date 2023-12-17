package ir.dealit.restful.config.app;

import ir.dealit.restful.client.SMSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class ExternalClientConfig {

    @Value("${services.sms.baseUrl}")
    private String smsBaseUrl;

    @Value("${arvan.s3.access_key}")
    private String s3AccessKey;

    @Value("${arvan.s3.secret_key}")
    private String s3SecretKey;

    @Value("${arvan.s3.endpoint}")
    private String s3Endpoint;

    @Bean
    public S3Client s3Client() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(s3AccessKey, s3SecretKey);
        return S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .endpointOverride(URI.create(s3Endpoint))
                .region(Region.of("ir-thr-at1"))
                .build();
    }

    @Bean
    public SMSClient smsClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl(smsBaseUrl)
                .defaultHeader("Accept", "*/*")
                .build();

        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
                        .build();

        return httpServiceProxyFactory.createClient(SMSClient.class);
    }
}
