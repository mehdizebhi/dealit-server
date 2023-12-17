package ir.dealit.restful.config.app;

import ir.dealit.restful.util.event.StartupEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class BeansConfig {

    @Bean
    public CommandLineRunner commandLineRunner(
            ApplicationEventPublisher applicationEventPublisher
    ) {
        return args -> {
            applicationEventPublisher.publishEvent(new StartupEvent(this));
        };
    }
}
