package ir.dealit.restful.module.user.repository;

import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {

    UserEntity findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    @Query("{'name':  ?0}")
    Optional<UserEntity> findByUsernameOptionally(String username);

    Integer countByUsernameOrEmail(String username, String email);

    Integer countByUsername(String username);

    Integer countByEmail(String email);

    @Query(value = "{'_id': ?0}", fields = "{'accounts': 1, '_id': 0}")
    Optional<List<String>> findAccountByUserId(ObjectId id);
}
