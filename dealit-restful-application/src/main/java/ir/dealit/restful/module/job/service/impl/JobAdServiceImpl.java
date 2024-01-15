package ir.dealit.restful.module.job.service.impl;

import ir.dealit.restful.dto.enums.JobAdStatus;
import ir.dealit.restful.dto.job.ChangeJobAd;
import ir.dealit.restful.dto.job.JobAd;
import ir.dealit.restful.dto.job.JobFilter;
import ir.dealit.restful.dto.job.NewJobAd;
import ir.dealit.restful.module.job.entity.FieldEntity;
import ir.dealit.restful.module.job.entity.JobAdEntity;
import ir.dealit.restful.module.job.entity.SkillEntity;
import ir.dealit.restful.module.job.repository.*;
import ir.dealit.restful.module.job.service.JobAdService;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.util.exception.JobNotFoundException;
import ir.dealit.restful.util.exception.NotFoundResourceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobAdServiceImpl implements JobAdService {

    private final JobAdRepository jobAdRepository;
    private final JobAdSearchRepository jobAdSearchRepository;
    private final SkillRepository skillRepository;
    private final FieldRepository fieldRepository;
    private final JobPositionRepository jobPositionRepository;

    @Override
    public JobAd jobAdDetails(ObjectId id) {
        var jobAdOp = jobAdRepository.findById(id);
        if (jobAdOp.isPresent()) {
            return toModel(jobAdOp.get());
        }
        throw new JobNotFoundException(HttpStatus.NOT_FOUND);
    }

    @Override
    public Page<JobAd> jobAdsDetailsByOwner(Pageable pageable, UserEntity owner) {
        return jobAdRepository.findByOwner(owner, pageable)
                .map(entity -> toModel(entity));
    }

    @Override
    public Page<JobAd> globalSearch(JobFilter filter, Pageable pageable, UserEntity requester) {
        return jobAdSearchRepository.searchByFilter(filter, pageable)
                .map(entity -> toModel(entity));
    }

    @Override
    @Transactional
    public ObjectId createJobAd(NewJobAd newJobAd, UserEntity user) {
        JobAdEntity entity = JobAdEntity.builder().build();
        BeanUtils.copyProperties(newJobAd, entity);

        var fieldOp = fieldRepository.findFirstByTitle(newJobAd.field());
        if (!fieldOp.isPresent()) {
            entity.setField(fieldRepository.save(FieldEntity.builder().title(newJobAd.field()).build()));
        } else {
            entity.setField(fieldOp.get());
        }

        var skillList = new ArrayList<SkillEntity>();
        for (var skill : newJobAd.skills()) {
            var skillOp = skillRepository.findFirstByTitle(skill);
            if (!skillOp.isPresent()) {
                skillList.add(skillRepository.save(SkillEntity.builder().title(skill).build()));
            } else {
                skillList.add(skillOp.get());
            }
        }

        var positionOp = jobPositionRepository.findById(new ObjectId(newJobAd.jobPositionId()));
        if (!positionOp.isPresent()) {
            throw new NotFoundResourceException("The job position id not found");
        }
        entity.setJobPosition(positionOp.get());
        entity.setSkills(skillList);
        entity.setStatus(JobAdStatus.ACTIVE);
        entity.setOwner(user);
        entity = jobAdRepository.save(entity);
        return entity.getId();
    }

    @Override
    @Transactional
    public void updateJobAd(ChangeJobAd jobAd, UserEntity user) {

    }

    @Override
    @Transactional
    public void removeJobAd(ObjectId id, UserEntity user) {

    }

    private JobAd toModel(JobAdEntity entity) {
        var model = JobAd.builder().build();
        BeanUtils.copyProperties(entity, model);
        model.setId(entity.getId().toString());
        model.setField(entity.getField().getTitle());
        model.setOwnerId(entity.getOwner().getId().toString());
        model.setJobPositionId(entity.getJobPosition().getId().toString());
        model.setSkills(entity.getSkills().stream().map(skillEntity -> skillEntity.getTitle()).collect(Collectors.toList()));
        model.setCreatedAt(new DateTime(entity.getCreatedAt()));
        model.setUpdatedAt(new DateTime(entity.getUpdatedAt()));
        return model;
    }
}
