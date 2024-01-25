package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.job.JobPosition;
import ir.dealit.restful.dto.job.ProjectSpaceDetails;
import ir.dealit.restful.dto.project.ProjectSpace;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/v1/projects")
@Secured("ROLE_CLIENT")
public interface QueryProjectSpaceApi {

    @GetMapping("")
    ResponseEntity<ResponseModel<List<ProjectSpace>>> getProjectSpaces(
            @PageableDefault(sort = {"updatedAt"}, direction = Sort.Direction.DESC) Pageable pageable,
            Authentication authentication
    );

    @GetMapping("/{spaceId}")
    ResponseEntity<ResponseModel<ProjectSpace>> getProjectSpace(
            @PathVariable("spaceId") ObjectId spaceId,
            Authentication authentication
    );

    @GetMapping("/{spaceId}/positions")
    ResponseEntity<ResponseModel<List<JobPosition>>>  getAllJobPositions(
            @PathVariable("spaceId") ObjectId spaceId,
            @PageableDefault(sort = {"updatedAt"}, direction = Sort.Direction.DESC) Pageable pageable,
            Authentication authentication
    );

    @GetMapping("/{spaceId}/positions/{positionId}")
    ResponseEntity<ResponseModel<JobPosition>> getJobPosition(
            @PathVariable("spaceId") ObjectId spaceId,
            @PathVariable("positionId") ObjectId positionId,
            Authentication authentication
    );
}
