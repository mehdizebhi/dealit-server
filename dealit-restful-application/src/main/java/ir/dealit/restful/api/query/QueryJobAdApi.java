package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.enums.ExperienceLevel;
import ir.dealit.restful.dto.enums.ProjectLength;
import ir.dealit.restful.dto.enums.WeeklyLoad;
import ir.dealit.restful.dto.job.JobAd;
import ir.dealit.restful.dto.job.JobFilter;
import ir.dealit.restful.dto.job.SubmitRange;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/jobs")
public interface QueryJobAdApi {

    @GetMapping("/{id}")
    ResponseEntity<EntityModel<JobAd>> getJobAd(
            @PathVariable ObjectId id,
            Authentication authentication
    );

    @GetMapping("/all")
    ResponseEntity<PagedModel<JobAd>> getAllJobAds(
            @PageableDefault Pageable pageable,
            Authentication authentication
    );

/*    @GetMapping("/")
    ResponseEntity<PagedModel<JobAd>> getJobAds(
            @PageableDefault Pageable pageable,
            @RequestBody JobFilter filter,
            Authentication authentication
    );*/

    @GetMapping("")
    ResponseEntity<PagedModel<JobAd>> getJobAds(
            @PageableDefault Pageable pageable,
            @RequestParam("search") String search,
            @RequestParam(value = "min", required = false) Double min,
            @RequestParam(value = "max", required = false) Double max,
            @RequestParam(value = "range", required = false) SubmitRange range,
            @RequestParam(value = "lengths", required = false) List<ProjectLength> lengths,
            @RequestParam(value = "loads", required = false) List<WeeklyLoad> loads,
            @RequestParam(value = "levels", required = false) List<ExperienceLevel> levels,
            @RequestParam(value = "fixed", required = false) Boolean fixed,
            @RequestParam(value = "hourly", required = false) Boolean hourly,
            @RequestParam(value = "verified", required = false) Boolean verified,
            @RequestParam(value = "previous", required = false) Boolean previous,
            Authentication authentication
    );
}
