package ir.dealit.restful.module.user.service;

import ir.dealit.restful.dto.enums.AccountType;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

//@Secured("ROLE_USER")
public interface UserAuthService {
    Optional<UserEntity> getUser(ObjectId userId);

    List<ObjectId> getAccountIds(ObjectId userId);

    Optional<ObjectId> getAccountId(UserEntity user, AccountType type);

    List<ObjectId> getRoleIds(ObjectId userId);

    List<AccountType> getAccountTypes(UserEntity user);

    Optional<ObjectId> getWalletId(ObjectId userId);

    Optional<ObjectId> getInboxId(ObjectId userId);

    Optional<ObjectId> getChatId(ObjectId userId);

    Optional<ObjectId> getJobSpaceId(ObjectId userId);

    Optional<ObjectId> getProfileId(ObjectId userId);
}
