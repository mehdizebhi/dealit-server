package ir.dealit.restful.module.job.service.impl;

import ir.dealit.restful.dto.project.NewProjectSpace;
import ir.dealit.restful.dto.project.ProjectSpace;
import ir.dealit.restful.dto.project.UpdateProjectSpace;
import ir.dealit.restful.module.job.entity.ProjectSpaceEntity;
import ir.dealit.restful.module.job.repository.JobPositionRepository;
import ir.dealit.restful.module.job.repository.ProjectSpaceRepository;
import ir.dealit.restful.module.job.service.ProjectSpaceService;
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
public class ProjectSpaceServiceImpl implements ProjectSpaceService {

    private final ProjectSpaceRepository projectSpaceRepository;
    private final JobPositionRepository jobPositionRepository;

    @Override
    public Page<ProjectSpace> allProjectSpacesByOwner(Pageable pageable, UserEntity owner) {
        var spaces = projectSpaceRepository.findByOwner(owner, pageable);
        return spaces.map(space -> ProjectSpace.builder()
                .id(space.getId().toString())
                .title(space.getTitle())
                .positions(jobPositionRepository.countByOwner(owner.getId()))
                .createdAt(new DateTime(space.getCreatedAt()))
                .updatedAt(new DateTime(space.getUpdatedAt()))
                .build());
    }

    @Override
    public ProjectSpace projectSpace(ObjectId spaceId, UserEntity owner) {
        var spaceOp = projectSpaceRepository.findByIdAndOwner(spaceId, owner);
        if (!spaceOp.isPresent()) {
            throw new NotFoundResourceException("The space id not found");
        }
        return spaceOp.map(space -> ProjectSpace.builder()
                .id(space.getId().toString())
                .title(space.getTitle())
                .positions(jobPositionRepository.countByOwner(owner.getId()))
                .createdAt(new DateTime(space.getCreatedAt()))
                .updatedAt(new DateTime(space.getUpdatedAt()))
                .build()).get();
    }

    @Override
    @Transactional
    public ObjectId createProjectSpace(NewProjectSpace projectSpace, UserEntity owner) {
        int count = projectSpaceRepository.countByTitleAndOwner(projectSpace.title(), owner);
        if (count > 0) {
            throw new DealitException("The space with title is already exist", HttpStatus.NOT_ACCEPTABLE);
        }
        var space = projectSpaceRepository.save(new ProjectSpaceEntity(projectSpace.title(), owner));
        return space.getId();
    }

    @Override
    public void updateProjectSpace(ObjectId spaceId, UpdateProjectSpace projectSpace, UserEntity owner) {
        var spaceOp = projectSpaceRepository.findByIdAndOwner(spaceId, owner);
        if (!spaceOp.isPresent()) {
            throw new NotFoundResourceException("The space id not found");
        }
        spaceOp.get().setTitle(projectSpace.title());
        projectSpaceRepository.save(spaceOp.get());
    }

    @Override
    public void deleteProjectSpace(ObjectId spaceId, UserEntity owner) {
        var spaceOp = projectSpaceRepository.findByIdAndOwner(spaceId, owner);
        if (!spaceOp.isPresent()) {
            throw new NotFoundResourceException("The space id not found");
        }
        if (jobPositionRepository.countByOwnerAndSpace(owner.getId(), spaceId) == 0) {
            projectSpaceRepository.deleteById(spaceId);
            return;
        }
        throw new NotDeletableException("The project space cannot be deleted due to dependence on other resources");
    }
}
