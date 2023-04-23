package ir.dealit.restful.repository.user;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserDetails, ObjectId> {

    UserDetails findByUsername(String username);
}
