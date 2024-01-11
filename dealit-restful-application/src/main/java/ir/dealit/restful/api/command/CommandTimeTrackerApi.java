package ir.dealit.restful.api.command;

import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.timetracker.RecordTime;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/v1/times")
public interface CommandTimeTrackerApi {

    @PostMapping("")
    ResponseEntity<ResponseModel<Map<String, String>>> addTime(
      @RequestBody RecordTime recordTime,
      Authentication authentication
    );

    @PatchMapping("/{id}")
    ResponseEntity<ResponseModel<Void>> updateTime(
            @PathVariable ObjectId id,
            @RequestBody RecordTime recordTime,
            Authentication authentication
    );

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseModel<Void>> deleteTime(
            @PathVariable ObjectId id,
            Authentication authentication
    );
}
