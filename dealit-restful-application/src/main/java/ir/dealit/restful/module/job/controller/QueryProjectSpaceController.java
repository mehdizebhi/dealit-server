package ir.dealit.restful.module.job.controller;

import ir.dealit.restful.api.query.QueryProjectSpaceApi;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.job.JobPosition;
import ir.dealit.restful.dto.project.ProjectSpace;
import ir.dealit.restful.module.job.service.JobPositionService;
import ir.dealit.restful.module.job.service.ProjectSpaceService;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
public class QueryProjectSpaceController implements QueryProjectSpaceApi {

    private final ProjectSpaceService projectSpaceService;
    private final JobPositionService jobPositionService;

    @Override
    public ResponseEntity<ResponseModel<List<ProjectSpace>>> getProjectSpaces(Pageable pageable, Authentication authentication) {
        var spaces = projectSpaceService.allProjectSpacesByOwner(pageable, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new ResponseModel.Builder<List<ProjectSpace>>()
                .data(spaces.toList())
                .pageMetadata(spaces)
                .success()
                .build()
        );
    }

    @Override
    public ResponseEntity<ResponseModel<ProjectSpace>> getProjectSpace(ObjectId spaceId, Authentication authentication) {
        var space = projectSpaceService.projectSpace(spaceId, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new ResponseModel.Builder<ProjectSpace>()
                .data(space)
                .success()
                .build()
        );
    }

    @Override
    public ResponseEntity<ResponseModel<List<JobPosition>>> getAllJobPositions(ObjectId spaceId, Pageable pageable, Authentication authentication) {
        var positions = jobPositionService.positionsByOwner(spaceId, pageable, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new ResponseModel.Builder<List<JobPosition>>()
                .data(positions.toList())
                .pageMetadata(positions)
                .success()
                .build()
        );
    }

    @Override
    public ResponseEntity<ResponseModel<JobPosition>> getJobPosition(ObjectId spaceId, ObjectId positionId, Authentication authentication) {
        var position = jobPositionService.position(positionId, spaceId, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new ResponseModel.Builder<JobPosition>()
                .data(position)
                .success()
                .build()
        );
    }
}
