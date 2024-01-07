package ir.dealit.restful.module.project.repository;

import ir.dealit.restful.module.project.entity.ProjectSpaceEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectSpaceRepository extends MongoRepository<ProjectSpaceEntity, ObjectId> {

    @Query(value = "{'owner': ?0}", count = true)
    Integer countByOwner(ObjectId userId);

    @Query(value = "{'owner': ?0}", fields = "{'_id': 0, 'jobPositions': 1}", count = true)
    Integer countJobPositionsByOwner(ObjectId userId);

    Page<ProjectSpaceEntity> findByOwner(UserEntity owner, Pageable pageable);
}
