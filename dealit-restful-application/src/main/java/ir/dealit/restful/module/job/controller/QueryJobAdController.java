package ir.dealit.restful.module.job.controller;

import ir.dealit.restful.api.query.QueryJobAdApi;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.enums.ExperienceLevel;
import ir.dealit.restful.dto.enums.ProjectLength;
import ir.dealit.restful.dto.enums.WeeklyLoad;
import ir.dealit.restful.dto.job.JobAd;
import ir.dealit.restful.dto.job.JobField;
import ir.dealit.restful.dto.job.JobFilter;
import ir.dealit.restful.dto.job.SubmitRange;
import ir.dealit.restful.module.job.service.JobAdService;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QueryJobAdController implements QueryJobAdApi {

    private final JobAdService jobAdService;

    @Override
    public ResponseEntity<ResponseModel<JobAd>> getJobAd(ObjectId id, Authentication authentication) {
        var model = new ResponseModel.Builder<JobAd>()
                .data(jobAdService.jobAdDetails(id))
                .success()
                .build();

        return ResponseEntity.ok(model);
    }

    @Override
    public ResponseEntity<ResponseModel<List<JobAd>>> exploreJobAd(Pageable pageable, Authentication authentication) {
        var models = jobAdService.allJobAdsForUser(pageable, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new ResponseModel.Builder<List<JobAd>>()
                .data(models.toList())
                .pageMetadata(models)
                .success()
                .build());
    }

    @Override
    public ResponseEntity<ResponseModel<List<JobAd>>> searchJobAd(String search,
                                                                  Double min,
                                                                  Double max,
                                                                  SubmitRange range,
                                                                  List<ProjectLength> lengths,
                                                                  List<WeeklyLoad> loads,
                                                                  List<ExperienceLevel> levels,
                                                                  boolean fixed,
                                                                  boolean hourly,
                                                                  boolean verified,
                                                                  boolean previous,
                                                                  Pageable pageable,
                                                                  Authentication authentication) {
        var filter = JobFilter.builder()
                .search(search)
                .minPrice(min)
                .maxPrice(max)
                .submitRange(range)
                .projectLengths(lengths)
                .weeklyLoads(loads)
                .experienceLevels(levels)
                .fixedPrice(fixed)
                .hourly(hourly)
                .paymentVerified(verified)
                .fromPreviousClients(previous)
                .build();

        var models = jobAdService
                .globalSearch(filter, pageable, (UserEntity) authentication.getPrincipal());

        return ResponseEntity.ok(new ResponseModel.Builder<List<JobAd>>()
                .data(models.toList())
                .pageMetadata(models)
                .success()
                .build());
    }

    @Override
    public ResponseEntity<ResponseModel<List<JobField>>> getJobFields() {
        return ResponseEntity.ok(new ResponseModel.Builder<List<JobField>>()
                .data(jobAdService.allJobField())
                .success()
                .build());
    }

    /*@Override
    public ResponseEntity<PagedModel<JobAd>> getJobAds(Pageable pageable, JobFilter filter, Authentication authentication) {
        return jobAdService
                .allJobAdsByFilter(pageable, filter, authentication)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }*/
}
