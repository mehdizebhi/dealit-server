package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.enums.ExperienceLevel;
import ir.dealit.restful.dto.enums.ProjectLength;
import ir.dealit.restful.dto.enums.WeeklyLoad;
import ir.dealit.restful.dto.job.JobAd;
import ir.dealit.restful.dto.job.SubmitRange;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/jobs")
public interface QueryJobAdApi {

    @GetMapping("/{id}")
    ResponseEntity<ResponseModel<JobAd>> getJobAd(
            @PathVariable ObjectId id,
            Authentication authentication
    );

    @GetMapping("/all")
    ResponseEntity<ResponseModel<List<JobAd>>> getMyJobAds(
            @PageableDefault Pageable pageable,
            Authentication authentication
    );

    @GetMapping("")
    ResponseEntity<ResponseModel<List<JobAd>>> getJobAds(
            @RequestParam("search") String search,
            @RequestParam(value = "min", required = false) Double min,
            @RequestParam(value = "max", required = false) Double max,
            @RequestParam(value = "range", required = false) SubmitRange range,
            @RequestParam(value = "lengths", required = false) List<ProjectLength> lengths,
            @RequestParam(value = "loads", required = false) List<WeeklyLoad> loads,
            @RequestParam(value = "levels", required = false) List<ExperienceLevel> levels,
            @RequestParam(value = "fixed", required = false) boolean fixed,
            @RequestParam(value = "hourly", required = false) boolean hourly,
            @RequestParam(value = "verified", required = false) boolean verified,
            @RequestParam(value = "previous", required = false) boolean previous,
            @PageableDefault Pageable pageable,
            Authentication authentication
    );

    /*    @GetMapping("/")
        ResponseEntity<PagedModel<JobAd>> getJobAds(
                @PageableDefault Pageable pageable,
                @RequestBody JobFilter filter,
                Authentication authentication
        );*/
}
