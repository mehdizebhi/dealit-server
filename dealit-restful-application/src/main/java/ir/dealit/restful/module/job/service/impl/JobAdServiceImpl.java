package ir.dealit.restful.module.job.service.impl;

import ir.dealit.restful.dto.enums.AccountType;
import ir.dealit.restful.dto.enums.JobAdStatus;
import ir.dealit.restful.dto.job.ChangeJobAd;
import ir.dealit.restful.dto.job.JobAd;
import ir.dealit.restful.dto.job.JobFilter;
import ir.dealit.restful.dto.job.NewJobAd;
import ir.dealit.restful.module.job.entity.JobAdEntity;
import ir.dealit.restful.module.job.repository.JobAdRepository;
import ir.dealit.restful.module.job.service.JobAdService;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobAdServiceImpl implements JobAdService {

    private final JobAdRepository jobAdRepository;
    private final UserAuthService userAuthService;

    @Override
    public Page<JobAd> jobAds(Pageable pageable, UserEntity user) {
        return null;
    }

    @Override
    public Page<JobAd> jobAdsByFilter(Pageable pageable, JobFilter filter, UserEntity user) {
        return null;
    }

    @Override
    public Page<JobAd> allJobAdsByStatus(Pageable pageable, JobAdStatus status, UserEntity user) {
        return null;
    }

    @Override
    public Integer countClientJobAds(JobAdStatus status, UserEntity owner) {
        var accountId = userAuthService.getAccountId(owner, AccountType.CLIENT);
        if (accountId.isPresent()) {
            return jobAdRepository.countByStatusAndOwner(status, accountId.get());
        }
        return 0;
    }

    @Override
    public JobAd jobAd(ObjectId id, UserEntity user) {
        return null;
    }

    @Override
    public List<JobAdEntity> allJobAd(JobAdStatus status, UserEntity owner) {
        return jobAdRepository.findAllByStatusAndOwner(status, owner.getId());
    }

    @Override
    public Optional<ObjectId> createJobAd(NewJobAd newJobAd, UserEntity user) {
        return null;
    }

    @Override
    public void updateJobAd(ChangeJobAd jobAd, UserEntity user) {

    }

    @Override
    public void removeJobAd(ObjectId id, UserEntity user) {

    }
}
