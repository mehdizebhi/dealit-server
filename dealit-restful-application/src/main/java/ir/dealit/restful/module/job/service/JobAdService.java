package ir.dealit.restful.module.job.service;

import ir.dealit.restful.dto.job.ChangeJobAd;
import ir.dealit.restful.dto.job.JobAd;
import ir.dealit.restful.dto.job.JobFilter;
import ir.dealit.restful.dto.job.NewJobAd;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;

import java.util.Optional;

@Secured("ROLE_USER")
public interface JobAdService {

    JobAd jobAd(ObjectId id);

    @Secured("ROLE_CLIENT")
    Page<JobAd> jobAdsByOwner(Pageable pageable, UserEntity owner);

    Page<JobAd> jobAdsByFilter(Pageable pageable, JobFilter filter,UserEntity user);

    Optional<ObjectId> createJobAd(NewJobAd newJobAd, UserEntity user);

    void updateJobAd(ChangeJobAd jobAd, UserEntity user);

    void removeJobAd(ObjectId id, UserEntity user);
}
