package ir.dealit.restful.api.command;

import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.job.ChangeJobAd;
import ir.dealit.restful.dto.job.JobAd;
import ir.dealit.restful.dto.job.JobField;
import ir.dealit.restful.dto.job.NewJobAd;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/v1/jobs")
public interface CommandJobAdApi {

    @PostMapping("")
    @Secured("ROLE_CLIENT")
    ResponseEntity<ResponseModel<Map<String, String>>> createJobAd(
            @RequestBody NewJobAd newJobAd,
            Authentication authentication
    );

    @PutMapping("")
    @Secured("ROLE_CLIENT")
    ResponseEntity<Void> updateJobAd(
            @RequestBody ChangeJobAd jobAd,
            Authentication authentication
    );

    @DeleteMapping("/{id}")
    @Secured("ROLE_CLIENT")
    ResponseEntity<Void> deleteJobAd(
            @PathVariable ObjectId id,
            Authentication authentication
    );

    @PostMapping("/fields")
    ResponseEntity<ResponseModel<Map<String, String>>> createJobField(
            @RequestBody JobField jobField,
            Authentication authentication
    );
}
