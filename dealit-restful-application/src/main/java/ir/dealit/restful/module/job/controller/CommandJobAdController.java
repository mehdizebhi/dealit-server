package ir.dealit.restful.module.job.controller;

import ir.dealit.restful.api.command.CommandJobAdApi;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.job.ChangeJobAd;
import ir.dealit.restful.dto.job.NewJobAd;
import ir.dealit.restful.module.job.service.JobAdService;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.ResponseEntity.badRequest;

@RestController
@RequiredArgsConstructor
public class CommandJobAdController implements CommandJobAdApi {

    private final JobAdService jobAdService;

    @Override
    public ResponseEntity<ResponseModel<Map<String, String>>> createJobAd(NewJobAd newJobAd, Authentication authentication) {
        var id = jobAdService.createJobAd(newJobAd, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new ResponseModel.Builder<Map<String, String>>()
                .data(Map.of("id", id.toString()))
                .success()
                .build());
    }

    @Override
    public ResponseEntity<Void> updateJobAd(ChangeJobAd jobAd, Authentication authentication) {
        jobAdService.updateJobAd(jobAd, (UserEntity) authentication.getPrincipal());
        return ResponseEntity
                .status(204)
                .build();
    }

    @Override
    public ResponseEntity<Void> deleteJobAd(ObjectId id, Authentication authentication) {
        jobAdService.removeJobAd(id, (UserEntity) authentication.getPrincipal());
        return ResponseEntity
                .status(204)
                .build();
    }
}
