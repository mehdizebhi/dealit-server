package ir.dealit.restful.module.job.controller;

import ir.dealit.restful.api.command.CommandJobAdApi;
import ir.dealit.restful.dto.job.ChangeJobAd;
import ir.dealit.restful.dto.job.JobAd;
import ir.dealit.restful.dto.job.NewJobAd;
import ir.dealit.restful.module.job.service.JobAdService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.badRequest;

@RestController
@RequiredArgsConstructor
public class CommandJobAdController implements CommandJobAdApi {

    private final JobAdService jobAdService;

    /*@Override
    public ResponseEntity<JobAd> getJobAd(ObjectId id) {
        return jobAdDaoService.findById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(badRequest().build());
    }

    @Override
    public ResponseEntity<Collection<JobAd>> getAllJobAds(Pageable pageable) {
        return ResponseEntity.ok(
                jobAdDaoService.findAll(pageable)
                        .stream()
                        .map(assembler::toModel)
                        .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Collection<JobAd>> getJobAds(Pageable pageable, JobFilter filter) {
        return ResponseEntity.ok(
                jobAdDaoService.findByFilter(filter, pageable)
                        .stream()
                        .map(assembler::toModel)
                        .collect(Collectors.toList()));
    }*/

    @Override
    public ResponseEntity<Void> createJobAd(NewJobAd newJobAd, Authentication authentication) {
        var id = jobAdService.createJobAd(newJobAd, authentication);
        return id.isPresent() ? ResponseEntity
                .status(201)
                .header("Location", id.get().toString())
                .build() : badRequest().build();
    }

    @Override
    public ResponseEntity<Void> updateJobAd(ChangeJobAd jobAd, Authentication authentication) {
        jobAdService.updateJobAd(jobAd, authentication);
        return ResponseEntity
                .status(204)
                .build();
    }

    @Override
    public ResponseEntity<Void> deleteJobAd(ObjectId id, Authentication authentication) {
        jobAdService.removeJobAd(id, authentication);
        return ResponseEntity
                .status(204)
                .build();
    }
}
