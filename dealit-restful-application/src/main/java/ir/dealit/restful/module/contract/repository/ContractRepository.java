package ir.dealit.restful.module.contract.repository;

import ir.dealit.restful.module.contract.entity.ContractEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends MongoRepository<ContractEntity, ObjectId> {
}
