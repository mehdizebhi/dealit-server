package ir.dealit.restful.api.query;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@RequestMapping("/v1/times")
public interface QueryTimeTrackerApi {

    // Todo: what we need here
    @GetMapping("")
    ResponseEntity<?> getTimes(
            @RequestParam("date") LocalDateTime dateTime
    );
}
