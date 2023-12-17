package ir.dealit.restful.module.job.controller;

import ir.dealit.restful.api.query.QueryJobAdApi;
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
import org.springframework.hateoas.EntityModel;
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
    public ResponseEntity<EntityModel<JobAd>> getJobAd(ObjectId id, Authentication authentication) {
        var model = EntityModel.of(jobAdService
                .jobAd(id, (UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(model);
    }

    @Override
    public ResponseEntity<PagedModel<JobAd>> getAllJobAds(Pageable pageable, Authentication authentication) {
        var models = toPagedModel(jobAdService
                .jobAds(pageable, (UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(models);
    }

    @Override
    public ResponseEntity<PagedModel<JobAd>> getJobAds(Pageable pageable, String search, Double min, Double max, SubmitRange range,
                                                       List<ProjectLength> lengths, List<WeeklyLoad> loads, List<ExperienceLevel> levels,
                                                       Boolean fixed, Boolean hourly, Boolean verified, Boolean previous,
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
