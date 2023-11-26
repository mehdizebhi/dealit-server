package ir.dealit.restful.module.job.repository;

import ir.dealit.restful.module.job.entity.ProposalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends MongoRepository<ProposalEntity, ObjectId> {
}
