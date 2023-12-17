package ir.dealit.restful.module.account.repository;

import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<AccountEntity, ObjectId> {

    @Query(value = "{'user': ?0}")
    Optional<AccountEntity> findByUserId(ObjectId userId);

    @Query(value = "{'user': ?0, '_class': ?1}")
    Optional<AccountEntity> findByUserIdAndType(ObjectId userId, String type);

    List<AccountEntity> findAllByUser(UserEntity user);
}
