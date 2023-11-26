package ir.dealit.restful.module.job.service;

import ir.dealit.restful.dto.job.ChangeJobAd;
import ir.dealit.restful.dto.job.JobAd;
import ir.dealit.restful.dto.job.JobFilter;
import ir.dealit.restful.dto.job.NewJobAd;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface JobAdService {
    //-----------------------------
    // Fetch Data
    //-----------------------------
    Optional<PagedModel<JobAd>> allJobAds(Pageable pageable, Authentication authentication);

    Optional<PagedModel<JobAd>> allJobAdsByFilter(Pageable pageable, JobFilter filter, Authentication authentication);

    Optional<EntityModel<JobAd>> jobAd(ObjectId id, Authentication authentication);

    //-----------------------------
    // Manipulate Data
    //-----------------------------
    Optional<ObjectId> createJobAd(NewJobAd newJobAd, Authentication authentication);

    void updateJobAd(ChangeJobAd jobAd, Authentication authentication);

    void removeJobAd(ObjectId id, Authentication authentication);
}
