package ir.dealit.restful.module.job.repository;

import ir.dealit.restful.module.job.entity.ProjectSpaceEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectSpaceRepository extends MongoRepository<ProjectSpaceEntity, ObjectId> {

    @Query(value = "{'owner': ?0}", count = true)
    Integer countByOwner(ObjectId userId);

    Integer countByTitleAndOwner(String title, UserEntity owner);

    Page<ProjectSpaceEntity> findByOwner(UserEntity owner, Pageable pageable);

    Optional<ProjectSpaceEntity> findByIdAndOwner(ObjectId id, UserEntity owner);
}
