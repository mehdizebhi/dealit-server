package ir.dealit.restful.module.wallet.repository;

import ir.dealit.restful.module.wallet.entity.WalletEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends MongoRepository<WalletEntity, ObjectId> {
}
