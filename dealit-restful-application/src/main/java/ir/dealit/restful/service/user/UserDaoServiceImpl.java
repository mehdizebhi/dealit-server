package ir.dealit.restful.service.user;

import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.dto.user.User;
import ir.dealit.restful.entity.user.UserEntity;
import ir.dealit.restful.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDaoServiceImpl implements UserDetailsService, UserDaoService {

    private final UserRepository repository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    @Override
    public Optional<UserEntity> findUserById(ObjectId id) {
        return repository.findById(id);
    }

    @Override
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

    @Override
    public Optional<UserEntity> partialUpdateUser(User user) {
        return Optional.empty();
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return repository.findAll();
    }

    private UserEntity toEntity(NewUser newUser) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(newUser, entity);
        entity.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        return entity;
    }
}
