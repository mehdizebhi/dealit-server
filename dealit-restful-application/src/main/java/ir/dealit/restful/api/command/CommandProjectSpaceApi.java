package ir.dealit.restful.api.command;

import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.job.NewJobPosition;
import ir.dealit.restful.dto.job.UpdateJobPosition;
import ir.dealit.restful.dto.project.UpdateProjectSpace;
import ir.dealit.restful.dto.project.NewProjectSpace;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/v1/projects")
@Secured("ROLE_CLIENT")
public interface CommandProjectSpaceApi {

    @PostMapping({"", "/"})
    ResponseEntity<ResponseModel<Map<String, String>>> createProject(
            @RequestBody NewProjectSpace newProjectSpace,
            Authentication authentication
    );

    @PatchMapping("/{spaceId}")
    ResponseEntity<ResponseModel<Void>> updateProject(
            @PathVariable("spaceId") ObjectId spaceId,
            @RequestBody UpdateProjectSpace projectSpace,
            Authentication authentication
    );

    @DeleteMapping("/{spaceId}")
    ResponseEntity<ResponseModel<Void>> deleteProject(
            @PathVariable("spaceId") ObjectId spaceId,
            Authentication authentication
    );

    @PostMapping("/{spaceId}")
    ResponseEntity<ResponseModel<Map<String, String>>> createJobPosition(
            @PathVariable("spaceId") ObjectId spaceId,
            @RequestBody NewJobPosition newJobPosition,
            Authentication authentication
    );

    @PatchMapping("/{spaceId}/{positionId}")
    ResponseEntity<ResponseModel<Void>> updateJobPosition(
            @PathVariable("spaceId") ObjectId spaceId,
            @PathVariable("positionId") ObjectId positionId,
            @RequestBody UpdateJobPosition position,
            Authentication authentication
    );

    @DeleteMapping("/{spaceId}/{positionId}")
    ResponseEntity<ResponseModel<Void>> deleteJobPosition(
            @PathVariable("spaceId") ObjectId spaceId,
            @PathVariable("positionId") ObjectId positionId,
            Authentication authentication
    );
}
