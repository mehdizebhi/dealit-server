package ir.dealit.restful.repository;

import ir.dealit.restful.repository.entity.AccountEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<AccountEntity, ObjectId> {
}
