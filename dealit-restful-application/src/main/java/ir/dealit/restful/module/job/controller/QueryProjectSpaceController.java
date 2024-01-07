package ir.dealit.restful.module.project.controller;

import ir.dealit.restful.api.query.QueryProjectSpaceApi;
import ir.dealit.restful.dto.job.JobPosition;
import ir.dealit.restful.dto.project.ProjectSpace;
import ir.dealit.restful.module.project.service.ProjectSpaceService;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
public class QueryProjectSpaceController implements QueryProjectSpaceApi {

    private final ProjectSpaceService projectSpaceService;

    @Override
    public ResponseEntity<PagedModel<ProjectSpace>> getAllProjectSpaces(Pageable pageable, Authentication authentication) {
        return projectSpaceService.allProjectSpacesByOwner(pageable, (UserEntity) authentication.getPrincipal())
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @Override
    public ResponseEntity<EntityModel<ProjectSpace>> getProjectSpace(ObjectId id, Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        return projectSpaceService
                .projectSpace(id, user.getId())
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @Override
    public ResponseEntity<PagedModel<JobPosition>> getAllJobPositions(ObjectId projectSpaceId, Pageable pageable, Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        return projectSpaceService
                .allJobPositions(projectSpaceId, pageable, user.getId())
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @Override
    public ResponseEntity<EntityModel<JobPosition>> getJobPosition(ObjectId projectSpaceId, ObjectId jobPositionId, Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        return projectSpaceService.
                jobPosition(projectSpaceId, jobPositionId, user.getId())
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }
}
