package ir.dealit.restful.repository;

import ir.dealit.restful.repository.entity.JobAdEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobAdRepository extends MongoRepository<JobAdEntity, ObjectId> {
}
