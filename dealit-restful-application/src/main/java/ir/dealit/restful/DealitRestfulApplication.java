package ir.dealit.restful;

import ir.dealit.restful.util.event.StartupEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class DealitRestfulApplication {

    public static void main(String[] args) {
        SpringApplication.run(DealitRestfulApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            ApplicationEventPublisher applicationEventPublisher
    ) {
        return args -> {
//            applicationEventPublisher.publishEvent(new StartupEvent(this));
        };
    }
}
