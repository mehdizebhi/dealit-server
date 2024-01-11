package ir.dealit.restful.module.timetracker.controller;

import ir.dealit.restful.api.command.CommandTimeTrackerApi;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.timetracker.RecordTime;
import ir.dealit.restful.module.timetracker.service.RecordTimeService;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CommandTimeTrackerController implements CommandTimeTrackerApi {

    private final RecordTimeService recordTimeService;

    @Override
    public ResponseEntity<ResponseModel<Map<String, String>>> addTime(RecordTime recordTime, Authentication authentication) {
        var id = recordTimeService.register(recordTime, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(
                new ResponseModel.Builder<Map<String, String>>()
                        .data(Map.of("id", id.toString()))
                        .success()
                        .build()
        );
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> updateTime(ObjectId id, RecordTime recordTime, Authentication authentication) {
        recordTimeService.update(id, recordTime, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(
                new ResponseModel.Builder<Void>()
                        .success()
                        .build()
        );
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> deleteTime(ObjectId id, Authentication authentication) {
        recordTimeService.delete(id, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(
                new ResponseModel.Builder<Void>()
                        .success()
                        .build()
        );
    }
}
