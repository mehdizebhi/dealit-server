package ir.dealit.restful.module.project.repository;

import ir.dealit.restful.module.project.entity.ProjectSpaceEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectSpaceRepository extends MongoRepository<ProjectSpaceEntity, ObjectId> {
}
