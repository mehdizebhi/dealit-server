package ir.dealit.restful.module.user.service;

import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.module.account.entity.ClientAccountEntity;
import ir.dealit.restful.module.account.entity.FreelancerAccountEntity;
import ir.dealit.restful.module.account.service.AccountDaoService;
import ir.dealit.restful.module.chat.repository.ChatRepository;
import ir.dealit.restful.module.inbox.repository.InboxRepository;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.repository.UserRepository;
import ir.dealit.restful.module.wallet.repository.WalletRepository;
import ir.dealit.restful.util.exception.UserFoundException;
import ir.dealit.restful.util.factory.AccountFactory;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
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
    private final WalletRepository walletRepository;
    private final InboxRepository inboxRepository;
    private final ChatRepository chatRepository;

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
            throw new UserFoundException(HttpStatus.NOT_ACCEPTABLE);
        }
        //Todo: validate UserEntity object
        UserEntity userEntity = toEntity(newUser);
        userEntity.addRoles(roleDaoService.loadRoleByName("ROLE_USER"));
        userEntity = repository.save(userEntity);
        userEntity.setWallet(walletRepository.save(AccountFactory.wallet(userEntity)));
        userEntity.setInbox(inboxRepository.save(AccountFactory.inbox(userEntity)));
        userEntity.setChat(chatRepository.save(AccountFactory.chat(userEntity)));
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
    public List<UserEntity> findAllUsers() {
        return repository.findAll();
    }

    @Transactional
    public Optional<UserEntity> registerAdmin(NewUser newUser) {
        Integer count = repository.countByUsernameOrEmail(
                newUser.getUsername(),
                newUser.getEmail());
        if (count > 0) {
            throw new UserFoundException(HttpStatus.NOT_ACCEPTABLE);
        }
        UserEntity userEntity = toEntity(newUser);
        userEntity.addRoles(roleDaoService.loadRoleByName("ROLE_USER"));
        userEntity = repository.save(userEntity);
        userEntity.setWallet(walletRepository.save(AccountFactory.wallet(userEntity)));
        userEntity.setInbox(inboxRepository.save(AccountFactory.inbox(userEntity)));
        userEntity.setChat(chatRepository.save(AccountFactory.chat(userEntity)));
        userEntity.addRoles(roleDaoService.loadRoleByName("ROLE_ADMIN"));
        userEntity = repository.save(userEntity);
        return Optional.of(userEntity);
    }

    private UserEntity toEntity(NewUser newUser) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(newUser, entity);
        entity.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        entity.setRoles(new HashSet<>());
        entity.setPictureHref("https://dealit.s3.ir-thr-at1.arvanstorage.ir/user.png");
        entity.setConnections(0);
        entity.setEnabled(true);
        entity.setAccountNonExpired(true);
        entity.setCredentialsNonExpired(true);
        entity.setAccountNonLocked(true);
        entity.setPhoneConfirmed(false);
        entity.setEmailConfirmed(false);
        return entity;
    }
}
