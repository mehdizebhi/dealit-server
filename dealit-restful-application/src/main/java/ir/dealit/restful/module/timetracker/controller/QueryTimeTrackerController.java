package ir.dealit.restful.module.timetracker.controller;

import ir.dealit.restful.api.query.QueryTimeTrackerApi;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.timetracker.RecordTime;
import ir.dealit.restful.module.timetracker.service.RecordTimeService;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class QueryTimeTrackerController implements QueryTimeTrackerApi {

    private final RecordTimeService recordTimeService;

    @Override
    public ResponseEntity<ResponseModel<RecordTime>> getRecordTime(ObjectId id, Authentication authentication) {
        return ResponseEntity.ok(
                new ResponseModel.Builder<RecordTime>()
                        .data(recordTimeService.recordTime(id, (UserEntity) authentication.getPrincipal()))
                        .success()
                        .build()
        );
    }

    @Override
    public ResponseEntity<ResponseModel<List<RecordTime>>> getRecordTimes(Pageable pageable, Authentication authentication) {
        var times = recordTimeService.recordTimes((UserEntity) authentication.getPrincipal(), pageable);
        return ResponseEntity.ok(
                new ResponseModel.Builder<List<RecordTime>>()
                        .data(times.toList())
                        .pageMetadata(times)
                        .success()
                        .build()
        );
    }
}
