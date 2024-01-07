package ir.dealit.restful.module.job.controller;

import ir.dealit.restful.api.command.CommandProjectSpaceApi;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.job.NewJobPosition;
import ir.dealit.restful.dto.job.UpdateJobPosition;
import ir.dealit.restful.dto.project.UpdateProjectSpace;
import ir.dealit.restful.dto.project.NewProjectSpace;
import ir.dealit.restful.module.job.service.JobPositionService;
import ir.dealit.restful.module.job.service.ProjectSpaceService;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CommandProjectSpaceController implements CommandProjectSpaceApi {

    private final ProjectSpaceService projectSpaceService;
    private final JobPositionService jobPositionService;

    @Override
    public ResponseEntity<ResponseModel<Map<String, String>>> createProject(NewProjectSpace newProjectSpace, Authentication authentication) {
        var id = projectSpaceService.createProjectSpace(newProjectSpace, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new ResponseModel.Builder<Map<String, String>>()
                .data(Map.of("id", id.toString()))
                .success()
                .build()
        );
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> updateProject(ObjectId spaceId, UpdateProjectSpace projectSpace, Authentication authentication) {
        projectSpaceService.updateProjectSpace(spaceId, projectSpace, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new ResponseModel.Builder<Void>()
                .success()
                .build()
        );
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> deleteProject(ObjectId spaceId, Authentication authentication) {
        projectSpaceService.deleteProjectSpace(spaceId, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new ResponseModel.Builder<Void>()
                .success()
                .build()
        );
    }

    @Override
    public ResponseEntity<ResponseModel<Map<String, String>>> createJobPosition(ObjectId spaceId, NewJobPosition newJobPosition, Authentication authentication) {
        var id = jobPositionService.newPosition(newJobPosition, spaceId, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new ResponseModel.Builder<Map<String, String>>()
                .data(Map.of("id", id.toString()))
                .success()
                .build());
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> updateJobPosition(ObjectId spaceId, ObjectId positionId, UpdateJobPosition position, Authentication authentication) {
        jobPositionService.updatePosition(positionId, spaceId, position, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new ResponseModel.Builder<Void>()
                .success()
                .build());
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> deleteJobPosition(ObjectId spaceId, ObjectId positionId, Authentication authentication) {
        jobPositionService.deletePosition(positionId, spaceId, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new ResponseModel.Builder<Void>()
                .success()
                .build());
    }
}
