package ir.dealit.restful.module.user.repository;

import ir.dealit.restful.module.user.entity.ConfirmationCodeEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ConfirmationCodeRepository extends MongoRepository<ConfirmationCodeEntity, ObjectId> {

    Optional<ConfirmationCodeEntity> findByCodeAndUserAndUsedAndExpireAtIsAfter(String code, UserEntity user, boolean used, Date time);
}
