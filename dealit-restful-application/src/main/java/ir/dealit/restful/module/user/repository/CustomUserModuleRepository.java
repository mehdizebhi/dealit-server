package ir.dealit.restful.module.user.repository;

import ir.dealit.restful.module.user.entity.ConfirmationCodeEntity;
import org.bson.types.ObjectId;

public interface CustomUserModuleRepository {
//    void updateConfirmationCode(ConfirmationCodeEntity confirmationCode);

    void updateConnections(ObjectId userId, int newConnections);
}
