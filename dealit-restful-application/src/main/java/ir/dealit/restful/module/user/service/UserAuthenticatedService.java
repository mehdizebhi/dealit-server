package ir.dealit.restful.module.user.service;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface UserAuthenticatedService {
    Optional<?> getUser(ObjectId userId);

    Optional<List<ObjectId>> getAccountIds(ObjectId userId);

    Optional<List<ObjectId>> getRoleIds(ObjectId userId);

    Optional<ObjectId> getWalletId(ObjectId userId);

    Optional<ObjectId> getInboxId(ObjectId userId);

    Optional<ObjectId> getChatId(ObjectId userId);

    Optional<List<ObjectId>> getProjectSpaceIds(ObjectId userId);

    Optional<ObjectId> getJobSpaceId(ObjectId userId);

    Optional<ObjectId> getProfileId(ObjectId userId);
}
