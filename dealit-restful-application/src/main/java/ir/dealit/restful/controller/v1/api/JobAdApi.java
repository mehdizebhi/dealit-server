package ir.dealit.restful.controller.v1.api;

import ir.dealit.restful.dto.job.JobAd;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@RequestMapping("/v1/jobs")
public interface JobAdApi {

    @GetMapping("/{id}")
    ResponseEntity<JobAd> getJobAd(
            @PathVariable String id
    );

    @GetMapping("")
    ResponseEntity<Collection<JobAd>> getJobAds(
            @PageableDefault(size = 10) Pageable pageable
    );

    @PostMapping("")
    ResponseEntity<String> createJobAd(
            @RequestPart JobAd jobAd,
            @RequestPart List<MultipartFile> files
    );

}
