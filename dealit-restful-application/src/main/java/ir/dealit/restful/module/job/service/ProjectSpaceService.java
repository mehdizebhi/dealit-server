package ir.dealit.restful.module.job.service;

import ir.dealit.restful.dto.job.JobPosition;
import ir.dealit.restful.dto.project.NewProjectSpace;
import ir.dealit.restful.dto.project.ProjectSpace;
import ir.dealit.restful.dto.project.UpdateProjectSpace;
import ir.dealit.restful.module.user.entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.access.annotation.Secured;

import java.util.Optional;

@Secured("ROLE_CLIENT")
public interface ProjectSpaceService {

    Page<ProjectSpace> allProjectSpacesByOwner(Pageable pageable, UserEntity owner);

    ProjectSpace projectSpace(ObjectId spaceId, UserEntity owner);

    ObjectId createProjectSpace(NewProjectSpace projectSpace, @NotNull UserEntity owner);

    void updateProjectSpace(ObjectId spaceId, UpdateProjectSpace projectSpace, @NotNull UserEntity owner);

    void deleteProjectSpace(ObjectId spaceId, @NotNull UserEntity owner);
}
