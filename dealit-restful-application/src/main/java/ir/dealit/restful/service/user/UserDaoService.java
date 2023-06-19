package ir.dealit.restful.service.user;

import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.dto.user.User;
import ir.dealit.restful.entity.user.UserEntity;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface UserDaoService {

    UserEntity findUserByUsername(String username);
    Optional<UserEntity> findUserById(ObjectId id);
    Optional<UserEntity> registerUser(NewUser newUser);
    Optional<UserEntity> partialUpdateUser(User user);
    List<UserEntity> findAllUsers();



}
