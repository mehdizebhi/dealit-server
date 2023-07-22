package ir.dealit.restful.service.dao;

import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.dto.user.User;
import ir.dealit.restful.repository.entity.UserEntity;
import ir.dealit.restful.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDaoService {

    private final UserRepository repository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public UserEntity findUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Optional<UserEntity> findUserById(ObjectId id) {
        return repository.findById(id);
    }


//    @Transactional
    public Optional<UserEntity> registerUser(NewUser newUser) {
        Integer count = repository.countByUsernameOrEmail(
                newUser.getUsername(),
                newUser.getEmail());
        if (count > 0) {
            throw new IllegalArgumentException("username or email is exist!");
        }
        //Todo: validate UserEntity object
        UserEntity userEntity = repository.save(toEntity(newUser));
        return Optional.of(userEntity);
    }


    public Optional<UserEntity> partialUpdateUser(User user) {
        return Optional.empty();
    }

    public List<UserEntity> findAllUsers() {
        return repository.findAll();
    }

    private UserEntity toEntity(NewUser newUser) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(newUser, entity);
        entity.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        // Set "true" value for some property for signup users
        entity.setEnabled(true);
        entity.setAccountNonExpired(true);
        entity.setCredentialsNonExpired(true);
        entity.setAccountNonLocked(true);
        return entity;
    }
}
