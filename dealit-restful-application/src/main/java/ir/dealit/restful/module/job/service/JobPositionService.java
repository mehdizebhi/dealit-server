package ir.dealit.restful.module.job.service;

import ir.dealit.restful.dto.job.JobPosition;
import ir.dealit.restful.dto.job.NewJobPosition;
import ir.dealit.restful.dto.job.UpdateJobPosition;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;

@Secured("ROLE_CLIENT")
public interface JobPositionService {

    JobPosition position(ObjectId positionId, ObjectId spaceId, UserEntity owner);

    Page<JobPosition> positionsByOwner(ObjectId spaceId, Pageable pageable, UserEntity owner);

    ObjectId newPosition(NewJobPosition newJobPosition, ObjectId spaceId, UserEntity owner);

    void updatePosition(ObjectId positionId, ObjectId spaceId, UpdateJobPosition updateJobPosition, UserEntity owner);

    void deletePosition(ObjectId positionId, ObjectId spaceId, UserEntity owner);
}
