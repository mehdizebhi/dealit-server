package ir.dealit.restful.module.job.controller;

import ir.dealit.restful.api.command.CommandJobApi;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.job.ChangeJobPosition;
import ir.dealit.restful.dto.job.NewJobPosition;
import ir.dealit.restful.dto.job.UpdateJobPosition;
import ir.dealit.restful.module.job.service.JobPositionService;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CommandJobController implements CommandJobApi {

    private final JobPositionService service;

    @Override
    public ResponseEntity<ResponseModel<Map<String, String>>> createJobPosition(NewJobPosition newJobPosition, Authentication authentication) {
        var id = service.newPosition(newJobPosition, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new ResponseModel.Builder<Map<String, String>>()
                .data(Map.of("id", id.toString()))
                .success()
                .build());
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> updateJobPosition(ObjectId positionId, UpdateJobPosition position, Authentication authentication) {
        service.updatePosition(positionId, position, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new ResponseModel.Builder<Void>()
                .success()
                .build());
    }

    @Override
    public ResponseEntity<Void> deleteJobPosition(ObjectId positionId, Authentication authentication) {
        return null;
    }
}
