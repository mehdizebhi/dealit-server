package ir.dealit.restful.api.command;

import ir.dealit.restful.dto.project.ChangeProjectSpace;
import ir.dealit.restful.dto.project.NewProjectSpace;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/projects")
public interface CommandProjectSpaceApi {

    @PostMapping("/")
    ResponseEntity<Void> createProject(
            @RequestBody NewProjectSpace newProjectSpace,
            Authentication authentication
    );

    @PutMapping("/")
    ResponseEntity<Void> updateProject(
            @RequestBody ChangeProjectSpace changeProjectSpace,
            Authentication authentication
    );

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProject(
            @PathVariable("id") ObjectId id,
            Authentication authentication
    );
}
