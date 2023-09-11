package ir.dealit.restful.repository;

import ir.dealit.restful.dto.account.Account;
import ir.dealit.restful.dto.user.UserInfo;
import ir.dealit.restful.repository.entity.AccountEntity;
import ir.dealit.restful.repository.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {

    UserEntity findByUsername(String username);

    @Query("{'name':  ?0}")
    Optional<UserEntity> findByUsernameOptionally(String username);

    Integer countByUsernameOrEmail(String username, String email);

    @Query(value = "{'_id': ?0}", fields = "{'accounts': 1, '_id': 0}")
    Optional<List<String>> findAccountByUserId(ObjectId id);

}
