package ir.dealit.restful.module.job.service.impl;

import ir.dealit.restful.dto.job.JobPosition;
import ir.dealit.restful.dto.job.NewJobPosition;
import ir.dealit.restful.dto.job.UpdateJobPosition;
import ir.dealit.restful.module.job.entity.JobPositionEntity;
import ir.dealit.restful.module.job.repository.JobPositionRepository;
import ir.dealit.restful.module.job.service.JobPositionService;
import ir.dealit.restful.module.job.repository.ProjectSpaceRepository;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.util.exception.DealitException;
import ir.dealit.restful.util.exception.NotDeletableException;
import ir.dealit.restful.util.exception.NotFoundResourceException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JobPositionServiceImpl implements JobPositionService {

    private final JobPositionRepository jobPositionRepository;
    private final ProjectSpaceRepository projectSpaceRepository;

    @Override
    public JobPosition position(ObjectId positionId, ObjectId spaceId, UserEntity owner) {
        var positionOp = jobPositionRepository.findByIdAndOwnerAndSpace(positionId, spaceId, owner.getId());
        if (!positionOp.isPresent()) {
            throw new NotFoundResourceException("The position id not found");
        }
        return JobPosition.builder()
                .id(positionOp.get().getId().toString())
                .title(positionOp.get().getTitle())
                .jobAds(positionOp.get().getJobAds().size())
                .contracts(positionOp.get().getContracts().size())
                .createdAt(new DateTime(positionOp.get().getCreatedAt()))
                .updatedAt(new DateTime(positionOp.get().getUpdatedAt()))
                .build();
    }

    @Override
    public Page<JobPosition> positionsByOwner(ObjectId spaceId, Pageable pageable, UserEntity owner) {
        var positions = jobPositionRepository.findByOwnerAndSpace(spaceId, owner.getId(), pageable);
        return positions.map(position -> JobPosition.builder()
                .id(position.getId().toString())
                .title(position.getTitle())
                .jobAds(position.getJobAds().size())
                .contracts(position.getContracts().size())
                .createdAt(new DateTime(position.getCreatedAt()))
                .updatedAt(new DateTime(position.getUpdatedAt()))
                .build());
    }

    @Override
    @Transactional
    public ObjectId newPosition(NewJobPosition newJobPosition, ObjectId spaceId, UserEntity owner) {
        var spaceOp = projectSpaceRepository.findByIdAndOwner(spaceId, owner);
        if (!spaceOp.isPresent()) {
            throw new NotFoundResourceException("The space id not found");
        }
        JobPositionEntity position = new JobPositionEntity(newJobPosition.title(), spaceOp.get(), owner);
        position = jobPositionRepository.save(position);
        return position.getId();
    }

    @Override
    @Transactional
    public void updatePosition(ObjectId positionId, ObjectId spaceId, UpdateJobPosition updateJobPosition, UserEntity owner) {
        var positionOp = jobPositionRepository.findByIdAndOwnerAndSpace(positionId, owner.getId(), spaceId);
        if (!positionOp.isPresent()) {
            throw new NotFoundResourceException("The position id not found");
        }
        var position = positionOp.get();
        position.setTitle(updateJobPosition.title());
        position.setStatus(updateJobPosition.status());
        jobPositionRepository.save(position);
    }

    @Override
    public void deletePosition(ObjectId positionId, ObjectId spaceId, UserEntity owner) {
        var positionOp = jobPositionRepository.findByIdAndOwnerAndSpace(positionId, owner.getId(), spaceId);
        if (!positionOp.isPresent()) {
            throw new NotFoundResourceException("The position id not found");
        }
        if (positionOp.get().getJobAds().isEmpty() && positionOp.get().getContracts().isEmpty()) {
            jobPositionRepository.deleteById(positionId);
            return;
        }
        throw new NotDeletableException("The job position cannot be deleted due to dependence on other resources");
    }
}
