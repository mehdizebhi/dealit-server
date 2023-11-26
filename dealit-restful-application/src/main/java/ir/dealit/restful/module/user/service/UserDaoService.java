package ir.dealit.restful.module.user.service;

import ir.dealit.restful.dto.enums.Authority;
import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.dto.user.User;
import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.module.account.entity.ClientAccountEntity;
import ir.dealit.restful.module.account.entity.FreelancerAccountEntity;
import ir.dealit.restful.module.account.service.AccountDaoService;
import ir.dealit.restful.module.user.entity.RoleEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.repository.RoleRepository;
import ir.dealit.restful.module.user.repository.UserRepository;
import ir.dealit.restful.util.exception.UserFoundExeption;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserDaoService {

    private final UserRepository repository;
    private final RoleDaoService roleDaoService;
    private final AccountDaoService accountDaoService;
    private final PasswordEncoder bCryptPasswordEncoder;

    public UserEntity findUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Optional<UserEntity> findUserById(ObjectId id) {
        return repository.findById(id);
    }


    @Transactional
    public Optional<UserEntity> registerUser(NewUser newUser) {
        Integer count = repository.countByUsernameOrEmail(
                newUser.getUsername(),
                newUser.getEmail());
        if (count > 0) {
            throw new UserFoundExeption("username or email is exist!");
        }
        //Todo: validate UserEntity object
        UserEntity userEntity = toEntity(newUser);
        userEntity.addRoles(roleDaoService.loadRoleByName("ROLE_USER"));
        userEntity = repository.save(userEntity);
        AccountEntity account = accountDaoService.setupAccount(userEntity, newUser.getAccount());
        userEntity.addAccount(account);
        if (account instanceof FreelancerAccountEntity) {
            userEntity.addRoles(roleDaoService.loadRoleByName("ROLE_FREELANCER"));
        } else if (account instanceof ClientAccountEntity) {
            userEntity.addRoles(roleDaoService.loadRoleByName("ROLE_CLIENT"));
        }
        userEntity = repository.save(userEntity);
        return Optional.of(userEntity);
    }

    public Optional<List<String>> findAllAccountsByUserId(ObjectId userId) {
        return repository.findAccountByUserId(userId);
//                .map(list ->
//                        list.stream().map(account -> account.getId().toString())
//                                .collect(Collectors.toList()));
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
        entity.setRoles(new HashSet<>());
        // Set "true" value for some property for signup users
        entity.setEnabled(true);
        entity.setAccountNonExpired(true);
        entity.setCredentialsNonExpired(true);
        entity.setAccountNonLocked(true);
        return entity;
    }
}
