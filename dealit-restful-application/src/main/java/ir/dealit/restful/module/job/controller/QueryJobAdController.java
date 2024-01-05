package ir.dealit.restful.module.job.controller;

import ir.dealit.restful.api.query.QueryJobAdApi;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.enums.ExperienceLevel;
import ir.dealit.restful.dto.enums.ProjectLength;
import ir.dealit.restful.dto.enums.WeeklyLoad;
import ir.dealit.restful.dto.job.JobAd;
import ir.dealit.restful.dto.job.JobFilter;
import ir.dealit.restful.dto.job.SubmitRange;
import ir.dealit.restful.module.job.service.JobAdService;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static ir.dealit.restful.util.helper.ControllerResponseHelper.*;

@RestController
@RequiredArgsConstructor
public class QueryJobAdController implements QueryJobAdApi {

    private final JobAdService jobAdService;

    @Override
    public ResponseEntity<ResponseModel<JobAd>> getJobAd(ObjectId id, Authentication authentication) {
        var model = new ResponseModel.Builder<JobAd>()
                .data(jobAdService.jobAd(id))
                .success()
                .build();

        return ResponseEntity.ok(model);
    }

    @Override
    public ResponseEntity<ResponseModel<List<JobAd>>> getMyJobAds(Pageable pageable, Authentication authentication) {
        var models = jobAdService.jobAdsByOwner(pageable, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new ResponseModel.Builder<List<JobAd>>()
                .data(models.toList())
                .pageMetadata(models)
                .success()
                .build());
    }

    @Override
    public ResponseEntity<PagedModel<JobAd>> getJobAds(String search,
                                                       Double min,
                                                       Double max,
                                                       SubmitRange range,
                                                       List<ProjectLength> lengths,
                                                       List<WeeklyLoad> loads,
                                                       List<ExperienceLevel> levels,
                                                       Boolean fixed,
                                                       Boolean hourly,
                                                       Boolean verified,
                                                       Boolean previous,
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
        var models = toPagedModel(jobAdService
                .jobAdsByFilter(pageable, filter, (UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(models);
    }

    /*@Override
    public ResponseEntity<PagedModel<JobAd>> getJobAds(Pageable pageable, JobFilter filter, Authentication authentication) {
        return jobAdService
                .allJobAdsByFilter(pageable, filter, authentication)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }*/
}
