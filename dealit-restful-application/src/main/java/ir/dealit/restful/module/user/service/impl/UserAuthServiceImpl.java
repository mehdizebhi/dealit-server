package ir.dealit.restful.module.user.service.impl;

import ir.dealit.restful.dto.enums.AccountType;
import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.module.account.entity.ClientAccountEntity;
import ir.dealit.restful.module.account.entity.FreelancerAccountEntity;
import ir.dealit.restful.module.account.repository.AccountRepository;
import ir.dealit.restful.module.user.entity.RoleEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.repository.UserRepository;
import ir.dealit.restful.module.user.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAuthServiceImpl implements UserAuthService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Override
    public Optional<UserEntity> getUser(ObjectId userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<ObjectId> getAccountIds(ObjectId userId) {
        var user = userRepository.findById(userId);
        return user
                .map(userEntity -> userEntity.getAccounts().stream().map(AccountEntity::getId).collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Override
    public Optional<ObjectId> getAccountId(UserEntity user, AccountType type) {
        var account = accountRepository.findByUserIdAndType(user.getId(), getAccountType(type));
        if (account.isPresent()) {
            return Optional.of(account.get().getId());
        }
        return Optional.empty();
    }

    @Override
    public List<ObjectId> getRoleIds(ObjectId userId) {
        var user = userRepository.findById(userId);
        return user
                .map(userEntity -> userEntity.getRoles().stream().map(RoleEntity::getId).collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Override
    public List<AccountType> getAccountTypes(UserEntity user) {
        List<AccountType> types = new ArrayList<>();
        var accounts = accountRepository.findAllByUser(user);
        for (var account : accounts) {
            if (account instanceof ClientAccountEntity) {
                types.add(AccountType.CLIENT);
            } else if (account instanceof FreelancerAccountEntity) {
                types.add(AccountType.FREELANCER);
            }
        }
        return types;
    }

    @Override
    public Optional<ObjectId> getWalletId(ObjectId userId) {
        var user = userRepository.findById(userId);
        return user
                .map(userEntity -> Optional.of(userEntity.getWallet().getId()))
                .orElse(Optional.empty());
    }

    @Override
    public Optional<ObjectId> getInboxId(ObjectId userId) {
        var user = userRepository.findById(userId);
        return user
                .map(userEntity -> Optional.of(userEntity.getInbox().getId()))
                .orElse(Optional.empty());
    }

    @Override
    public Optional<ObjectId> getChatId(ObjectId userId) {
        var user = userRepository.findById(userId);
        return user
                .map(userEntity -> Optional.of(userEntity.getChat().getId()))
                .orElse(Optional.empty());
    }

    @Override
    public List<ObjectId> getProjectSpaceIds(ObjectId userId) {
        var account = (ClientAccountEntity) accountRepository.findByUserIdAndType(userId, getAccountType(AccountType.CLIENT)).get();
        return account.getProjectSpaces().stream().map(project -> project.getId()).collect(Collectors.toList());
    }

    @Override
    public Optional<ObjectId> getJobSpaceId(ObjectId userId) {
        var account = (FreelancerAccountEntity) accountRepository.findByUserIdAndType(userId, getAccountType(AccountType.FREELANCER)).get();
        return Optional.of(account.getJobSpace().getId());
    }

    @Override
    public Optional<ObjectId> getProfileId(ObjectId userId) {
        var account = (FreelancerAccountEntity) accountRepository.findByUserIdAndType(userId, getAccountType(AccountType.FREELANCER)).get();
        return Optional.of(account.getProfile().getId());
    }

    private String getAccountType(AccountType type) {
        return switch (type) {
            case FREELANCER -> "FreelancerAccount";
            default -> "ClientAccount";
        };
    }
}
