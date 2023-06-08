package ir.dealit.restful.repository.user;

import ir.dealit.restful.entity.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUsername(String username);

    @Query("{'name':  ?0}")
    Optional<User> findByUsernameOptionally(String username);
}
