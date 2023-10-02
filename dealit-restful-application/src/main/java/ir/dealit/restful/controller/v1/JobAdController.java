package ir.dealit.restful.controller.v1;

import ir.dealit.restful.controller.v1.api.JobAdApi;
import ir.dealit.restful.dto.job.JobAd;
import ir.dealit.restful.dto.job.JobFilter;
import ir.dealit.restful.service.dao.JobAdDaoService;
import ir.dealit.restful.util.hateoas.assembler.JobAdModelAssembler;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequiredArgsConstructor
public class JobAdController implements JobAdApi {

    private final JobAdDaoService jobAdDaoService;
    private final JobAdModelAssembler assembler;

    @Override
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
    }

    @Override
    public ResponseEntity<JobAd> createJobAd(JobAd jobAd) {
        return jobAdDaoService.register(jobAd)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(badRequest().build());
    }

    @Override
    public ResponseEntity<JobAd> updateJobAd(JobAd jobAd) {
        return jobAdDaoService.update(jobAd)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @Override
    public ResponseEntity<Void> deleteJobAd(ObjectId id) {
        jobAdDaoService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
