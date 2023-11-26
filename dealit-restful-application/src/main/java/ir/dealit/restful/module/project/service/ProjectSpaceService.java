package ir.dealit.restful.module.project.service;

import ir.dealit.restful.dto.job.JobPosition;
import ir.dealit.restful.dto.project.ProjectSpace;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.Optional;

@Secured("ROLE_CLIENT")
public interface ProjectSpaceService {
    //--------------------------------------
    // Fetching Data
    //--------------------------------------
    Optional<PagedModel<ProjectSpace>> allProjectSpaces(Pageable pageable, @NotNull ObjectId userId);

    Optional<EntityModel<ProjectSpace>> projectSpace(ObjectId id, @NotNull ObjectId userId);

    Optional<PagedModel<JobPosition>> allJobPositions(ObjectId projectSpaceId, Pageable pageable, @NotNull ObjectId userId);

    Optional<EntityModel<JobPosition>> jobPosition(ObjectId projectSpaceId, ObjectId jobPositionId, @NotNull ObjectId userId);

    //--------------------------------------
    // Manipulate Data
    //--------------------------------------
    void createProjectSpace(ProjectSpace newProjectSpace, @NotNull ObjectId userId);

    void updateProjectSpace(ProjectSpace projectSpace, @NotNull ObjectId userId);

    void removeProjectSpace(ObjectId projectSpaceId, @NotNull ObjectId userId);

    void addJobPosition(JobPosition newJobPosition, @NotNull ObjectId userId);

    void updateJobPosition(JobPosition jobPosition, @NotNull ObjectId userId);

    void removeJobPosition(ObjectId projectSpaceId, @NotNull ObjectId jobPositionId, ObjectId userId);
}
