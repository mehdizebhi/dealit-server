package ir.dealit.restful.module.account.repository;

import ir.dealit.restful.module.account.entity.ClientAccountEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAccountRepository extends MongoRepository<ClientAccountEntity, ObjectId> {
}
