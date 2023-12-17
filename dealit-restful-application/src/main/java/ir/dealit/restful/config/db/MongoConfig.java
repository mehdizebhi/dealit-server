package ir.dealit.restful.config.db;

import ir.dealit.restful.util.DateReadConverter;
import ir.dealit.restful.util.DateWriteConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.core.convert.converter.Converter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableMongoAuditing
//@EnableMongoAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class MongoConfig {

    @Bean
    public MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

//    @Bean(name = "auditingDateTimeProvider")
//    public DateTimeProvider dateTimeProvider() {
//        return () -> Optional.of(OffsetDateTime.now());
//    }

    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new DateReadConverter());
        converters.add(new DateWriteConverter());
        return new MongoCustomConversions(converters);
    }
}
