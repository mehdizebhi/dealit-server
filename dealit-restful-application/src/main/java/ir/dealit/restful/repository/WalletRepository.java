package ir.dealit.restful.repository;

import ir.dealit.restful.repository.entity.WalletEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends MongoRepository<WalletEntity, ObjectId> {
}
