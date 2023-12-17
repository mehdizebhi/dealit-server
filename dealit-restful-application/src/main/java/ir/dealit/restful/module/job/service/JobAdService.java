package ir.dealit.restful.module.job.service;

import ir.dealit.restful.dto.enums.JobAdStatus;
import ir.dealit.restful.dto.job.ChangeJobAd;
import ir.dealit.restful.dto.job.JobAd;
import ir.dealit.restful.dto.job.JobFilter;
import ir.dealit.restful.dto.job.NewJobAd;
import ir.dealit.restful.module.job.entity.JobAdEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface JobAdService {
    //-----------------------------
    // Fetch Data
    //-----------------------------
    Page<JobAd> jobAds(Pageable pageable, UserEntity user);

    Page<JobAd> jobAdsByFilter(Pageable pageable, JobFilter filter,UserEntity user);

    Page<JobAd> allJobAdsByStatus(Pageable pageable, JobAdStatus status, UserEntity user);

    Integer countClientJobAds(JobAdStatus status, UserEntity owner);

    JobAd jobAd(ObjectId id, UserEntity user);

    List<JobAdEntity> allJobAd(JobAdStatus status, UserEntity owner);

    //-----------------------------
    // Manipulate Data
    //-----------------------------
    Optional<ObjectId> createJobAd(NewJobAd newJobAd, UserEntity user);

    void updateJobAd(ChangeJobAd jobAd, UserEntity user);

    void removeJobAd(ObjectId id, UserEntity user);
}
