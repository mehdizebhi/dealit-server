package ir.dealit.restful;

import ir.dealit.restful.service.SMSService;
import ir.dealit.restful.util.event.StartupEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DealitRestfulApplication {

    public static void main(String[] args) {
        SpringApplication.run(DealitRestfulApplication.class, args);
    }
}
