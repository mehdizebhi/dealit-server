package ir.dealit.restful.module.wallet.repository;

import ir.dealit.restful.module.wallet.entity.AssetEntity;
import ir.dealit.restful.module.wallet.entity.WalletEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends MongoRepository<WalletEntity, ObjectId> {

/*    @Query
    List<AssetEntity> findAssetsByUser(ObjectId userId);*/

}
