package ir.dealit.restful.api;

import ir.dealit.restful.dto.job.JobAd;
import ir.dealit.restful.dto.job.JobFilter;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/v1/jobs")
public interface JobAdApi {

    @GetMapping("/{id}")
    ResponseEntity<JobAd> getJobAd(
            @PathVariable ObjectId id
    );

    @GetMapping("/all")
    ResponseEntity<Collection<JobAd>> getAllJobAds(
            @PageableDefault Pageable pageable
    );

    @GetMapping("")
    ResponseEntity<Collection<JobAd>> getJobAds(
            @PageableDefault Pageable pageable,
            @RequestBody JobFilter filter
    );

    @PostMapping("")
    ResponseEntity<JobAd> createJobAd(
            @RequestBody JobAd jobAd
    );

    @PutMapping("")
    ResponseEntity<JobAd> updateJobAd(
            @RequestBody JobAd jobAd
    );

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteJobAd(
            @PathVariable ObjectId id
    );

}
