package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.timetracker.RecordTime;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/v1/times")
public interface QueryTimeTrackerApi {

    @GetMapping("/{id}")
    ResponseEntity<ResponseModel<RecordTime>> getRecordTime(
            @PathVariable ObjectId id,
            Authentication authentication
    );

    @GetMapping("")
    ResponseEntity<ResponseModel<List<RecordTime>>> getRecordTimes(
            @PageableDefault Pageable pageable,
            Authentication authentication
    );
}
