package ir.dealit.restful.module.job.service.impl;

import ir.dealit.restful.dto.job.ChangeJobAd;
import ir.dealit.restful.dto.job.JobAd;
import ir.dealit.restful.dto.job.JobFilter;
import ir.dealit.restful.dto.job.NewJobAd;
import ir.dealit.restful.module.job.service.JobAdService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobAdServiceImpl implements JobAdService {

    @Override
    public Optional<PagedModel<JobAd>> allJobAds(Pageable pageable, Authentication authentication) {
        return Optional.empty();
    }

    @Override
    public Optional<PagedModel<JobAd>> allJobAdsByFilter(Pageable pageable, JobFilter filter, Authentication authentication) {
        return Optional.empty();
    }

    @Override
    public Optional<EntityModel<JobAd>> jobAd(ObjectId id, Authentication authentication) {
        return Optional.empty();
    }

    @Override
    public Optional<ObjectId> createJobAd(NewJobAd newJobAd, Authentication authentication) {

        return null;
    }

    @Override
    public void updateJobAd(ChangeJobAd jobAd, Authentication authentication) {

    }

    @Override
    public void removeJobAd(ObjectId id, Authentication authentication) {

    }
}
