package ir.dealit.restful.api.command;

import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.job.NewJobPosition;
import ir.dealit.restful.dto.job.UpdateJobPosition;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/v1/positions")
public interface CommandJobPositionApi {

    /*@PostMapping("/")
    ResponseEntity<ResponseModel<Map<String, String>>> createJobPosition(
            @RequestBody NewJobPosition newJobPosition,
            Authentication authentication
    );

    @PatchMapping("/{id}")
    ResponseEntity<ResponseModel<Void>> updateJobPosition(
            @PathVariable("id") ObjectId id,
            @RequestBody UpdateJobPosition position,
            Authentication authentication
    );

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseModel<Void>> deleteJobPosition(
            @PathVariable("id") ObjectId id,
            Authentication authentication
    );*/
}
