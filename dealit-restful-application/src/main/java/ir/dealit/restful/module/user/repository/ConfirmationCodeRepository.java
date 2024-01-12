package ir.dealit.restful.module.user.repository;

import ir.dealit.restful.module.user.entity.ConfirmationCodeEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConfirmationCodeRepository extends MongoRepository<ConfirmationCodeEntity, ObjectId> {

    Optional<ConfirmationCodeEntity> findByCodeAndUserAndUsedAndReasonAndExpireAtIsAfter(String code, UserEntity user, boolean used, String reason, Date time);

    Optional<ConfirmationCodeEntity> findByCodeAndUsedAndExpireAtIsAfter(String code, boolean used, Date time);

    @Query(value = "{'user': ?0, 'used': false, 'reason': ?1, 'expireAt': {$gte: ?2}}")
    List<ConfirmationCodeEntity> findAnyValidCode(ObjectId userId, String reason, Date time);
}
