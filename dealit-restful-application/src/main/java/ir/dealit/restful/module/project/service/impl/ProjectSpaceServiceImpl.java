package ir.dealit.restful.module.project.service.impl;

import ir.dealit.restful.dto.job.JobPosition;
import ir.dealit.restful.dto.project.ProjectSpace;
import ir.dealit.restful.module.project.service.ProjectSpaceService;
import jakarta.validation.constraints.NotNull;
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
public class ProjectSpaceServiceImpl implements ProjectSpaceService {

    @Override
    public Optional<PagedModel<ProjectSpace>> allProjectSpaces(Pageable pageable, @NotNull ObjectId userId) {
        return Optional.empty();
    }

    @Override
    public Optional<EntityModel<ProjectSpace>> projectSpace(ObjectId id, @NotNull ObjectId userId) {
        return Optional.empty();
    }

    @Override
    public Optional<PagedModel<JobPosition>> allJobPositions(ObjectId projectSpaceId, Pageable pageable, @NotNull ObjectId userId) {
        return Optional.empty();
    }

    @Override
    public Optional<EntityModel<JobPosition>> jobPosition(ObjectId projectSpaceId, ObjectId jobPositionId, @NotNull ObjectId userId) {
        return Optional.empty();
    }

    @Override
    public void createProjectSpace(ProjectSpace newProjectSpace, @NotNull ObjectId userId) {

    }

    @Override
    public void updateProjectSpace(ProjectSpace projectSpace, @NotNull ObjectId userId) {

    }

    @Override
    public void removeProjectSpace(ObjectId projectSpaceId, @NotNull ObjectId userId) {

    }

    @Override
    public void addJobPosition(JobPosition newJobPosition, @NotNull ObjectId userId) {

    }

    @Override
    public void updateJobPosition(JobPosition jobPosition, @NotNull ObjectId userId) {

    }

    @Override
    public void removeJobPosition(ObjectId projectSpaceId, ObjectId jobPositionId, @NotNull ObjectId userId) {

    }
}
