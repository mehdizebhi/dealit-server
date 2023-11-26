package ir.dealit.restful.api.command;

import ir.dealit.restful.dto.job.ChangeJobPosition;
import ir.dealit.restful.dto.job.NewJobPosition;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/positions")
public interface CommandJobApi {

    @PostMapping("/")
    ResponseEntity<Void> createJobPosition(
            @RequestBody NewJobPosition newJobPosition,
            Authentication authentication
    );

    @PutMapping("/")
    ResponseEntity<Void> updateJobPosition(
            @RequestBody ChangeJobPosition changeJobPosition,
            Authentication authentication
    );

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteJobPosition(
            @PathVariable("id") ObjectId id,
            Authentication authentication
    );
}
