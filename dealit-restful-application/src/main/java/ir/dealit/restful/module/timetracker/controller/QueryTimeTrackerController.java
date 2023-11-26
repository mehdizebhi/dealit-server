package ir.dealit.restful.module.timetracker.controller;

import ir.dealit.restful.api.query.QueryTimeTrackerApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class QueryTimeTrackerController implements QueryTimeTrackerApi {
    @Override
    public ResponseEntity<?> getTimes(LocalDateTime dateTime) {
        return null;
    }
}
