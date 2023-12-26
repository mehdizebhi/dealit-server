package ir.dealit.restful.module.user.repository;

import ir.dealit.restful.module.user.entity.TokenEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends MongoRepository<TokenEntity, ObjectId> {
    Optional<TokenEntity> findByToken(String token);

    Optional<TokenEntity> findByRefreshToken(String refreshToken);

    void deleteByToken(String token);
}
