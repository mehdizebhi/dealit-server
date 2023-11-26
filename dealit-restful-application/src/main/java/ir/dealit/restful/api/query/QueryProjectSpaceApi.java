package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.job.JobPosition;
import ir.dealit.restful.dto.project.ProjectSpace;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/projects")
public interface QueryProjectSpaceApi {

    @GetMapping("")
    ResponseEntity<PagedModel<ProjectSpace>> getAllProjectSpaces(
            @PageableDefault Pageable pageable,
            Authentication authentication
    );

    @GetMapping("/{id}")
    ResponseEntity<EntityModel<ProjectSpace>> getProjectSpace(
            @PathVariable("id") ObjectId id,
            Authentication authentication
    );

    @GetMapping("/{projectID}/positions")
    ResponseEntity<PagedModel<JobPosition>> getAllJobPositions(
            @PathVariable("projectID") ObjectId projectSpaceId,
            @PageableDefault Pageable pageable,
            Authentication authentication
    );

    @GetMapping("/{projectID}/positions/{positionID}")
    ResponseEntity<EntityModel<JobPosition>> getJobPosition(
            @PathVariable("projectID") ObjectId projectSpaceId,
            @PathVariable("positionID") ObjectId jobPositionId,
            Authentication authentication
    );
}
