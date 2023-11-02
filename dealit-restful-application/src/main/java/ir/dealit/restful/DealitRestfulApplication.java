package ir.dealit.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class DealitRestfulApplication {

    public static void main(String[] args) {
        SpringApplication.run(DealitRestfulApplication.class, args);
    }

}
