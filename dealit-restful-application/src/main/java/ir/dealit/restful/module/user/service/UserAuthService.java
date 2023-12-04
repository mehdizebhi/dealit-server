package ir.dealit.restful.module.user.service;

import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.security.access.annotation.Secured;

import java.util.List;
import java.util.Optional;

//@Secured("ROLE_USER")
public interface UserAuthenticatedService {
    Optional<UserEntity> getUser(ObjectId userId);

    List<ObjectId> getAccountIds(ObjectId userId);

    List<ObjectId> getRoleIds(ObjectId userId);

    Optional<ObjectId> getWalletId(ObjectId userId);

    Optional<ObjectId> getInboxId(ObjectId userId);

    Optional<ObjectId> getChatId(ObjectId userId);

    List<ObjectId> getProjectSpaceIds(ObjectId userId);

    Optional<ObjectId> getJobSpaceId(ObjectId userId);

    Optional<ObjectId> getProfileId(ObjectId userId);
}
