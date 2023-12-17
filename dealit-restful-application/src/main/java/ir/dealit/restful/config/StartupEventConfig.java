package ir.dealit.restful.config;

import ir.dealit.restful.config.db.MongoMigration;
import ir.dealit.restful.util.event.StartupEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartupEventConfig {

    private final MongoMigration mongoMigration;

    @Value("${app.startup.migration}")
    private boolean migrationEnable;

    @Async
    @EventListener(StartupEvent.class)
    public void handleStartupEvent() {
        log.info("Application Starting up!");
        if (migrationEnable) mongoMigration.init();
        log.info("Application is ready now!");
    }
}
