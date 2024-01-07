package ir.dealit.restful.module.job.repository;

import ir.dealit.restful.module.job.entity.JobPositionEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobPositionRepository extends MongoRepository<JobPositionEntity, ObjectId> {

    Optional<JobPositionEntity> findByIdAndOwner(ObjectId id, UserEntity owner);

    @Query(value = "{'_id': ?0, 'space': ?1, 'owner': ?2}")
    Optional<JobPositionEntity> findByIdAndOwnerAndSpace(ObjectId positionId, ObjectId spaceId, ObjectId ownerId);

    @Query(value = "{'space': ?0, 'owner': ?1}")
    Page<JobPositionEntity> findByOwnerAndSpace(ObjectId spaceId, ObjectId ownerId, Pageable pageable);
}
