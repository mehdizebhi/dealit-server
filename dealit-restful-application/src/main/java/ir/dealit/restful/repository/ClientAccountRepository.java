package ir.dealit.restful.repository;

import ir.dealit.restful.repository.entity.ClientAccountEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAccountRepository extends MongoRepository<ClientAccountEntity, ObjectId> {
}
