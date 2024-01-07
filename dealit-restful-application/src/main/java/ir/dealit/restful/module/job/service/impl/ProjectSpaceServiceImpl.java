package ir.dealit.restful.module.project.service.impl;

import ir.dealit.restful.dto.job.JobPosition;
import ir.dealit.restful.dto.project.ProjectSpace;
import ir.dealit.restful.module.project.repository.ProjectSpaceRepository;
import ir.dealit.restful.module.project.service.ProjectSpaceService;
import ir.dealit.restful.module.user.entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectSpaceServiceImpl implements ProjectSpaceService {

    private final ProjectSpaceRepository projectSpaceRepository;

    @Override
    public Page<ProjectSpace> allProjectSpacesByOwner(Pageable pageable, UserEntity owner) {
        var spaces = projectSpaceRepository.findByOwner(owner, pageable);
        return spaces.map(space -> ProjectSpace.builder()
                .id(space.getId().toString())
                .title(space.getTitle())
                .positions(space.getJobPositions().size())
                .createdAt(new DateTime(space.getCreatedAt()))
                .updatedAt(new DateTime(space.getUpdatedAt()))
                .build());
    }

    @Override
    public ProjectSpace projectSpace(ObjectId spaceId, UserEntity owner) {
        return null;
    }

    @Override
    public ObjectId createProjectSpace(ProjectSpace newProjectSpace, ObjectId userId) {
        return null;
    }

    @Override
    public void updateProjectSpace(ProjectSpace projectSpace, ObjectId userId) {

    }

    @Override
    public void deleteProjectSpace(ObjectId projectSpaceId, ObjectId userId) {

    }
}
