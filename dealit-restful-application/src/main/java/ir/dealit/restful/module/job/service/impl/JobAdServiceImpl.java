package ir.dealit.restful.module.job.service.impl;

import ir.dealit.restful.dto.job.ChangeJobAd;
import ir.dealit.restful.dto.job.JobAd;
import ir.dealit.restful.dto.job.JobFilter;
import ir.dealit.restful.dto.job.NewJobAd;
import ir.dealit.restful.module.job.entity.JobAdEntity;
import ir.dealit.restful.module.job.repository.JobAdRepository;
import ir.dealit.restful.module.job.service.JobAdService;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.service.UserAuthService;
import ir.dealit.restful.util.exception.JobNotFoundException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobAdServiceImpl implements JobAdService {

    private final JobAdRepository jobAdRepository;
    private final UserAuthService userAuthService;

    @Override
    public JobAd jobAd(ObjectId id) {
        var jobAdOp = jobAdRepository.findById(id);
        if (jobAdOp.isPresent()) {
            return toModel(jobAdOp.get());
        }
        throw new JobNotFoundException(HttpStatus.NOT_FOUND);
    }

    @Override
    public Page<JobAd> jobAdsByOwner(Pageable pageable, UserEntity owner) {
        return jobAdRepository.findByOwner(owner, pageable)
                .map(entity -> toModel(entity));
    }

    @Override
    public Page<JobAd> jobAdsByFilter(Pageable pageable, JobFilter filter, UserEntity user) {
        return null;
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

    private JobAd toModel(JobAdEntity entity) {
        var model = JobAd.builder().build();
        BeanUtils.copyProperties(entity, model);
        return model;
    }
}
