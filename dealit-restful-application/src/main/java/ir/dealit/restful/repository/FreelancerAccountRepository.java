package ir.dealit.restful.repository;

import ir.dealit.restful.repository.entity.FreelancerAccountEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreelancerAccountRepository extends MongoRepository<FreelancerAccountEntity, ObjectId> {
}
