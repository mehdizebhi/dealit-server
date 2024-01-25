package ir.dealit.restful.module.inbox.repository;

import ir.dealit.restful.module.inbox.entity.InboxEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InboxRepository extends MongoRepository<InboxEntity, ObjectId> {

    List<InboxEntity> findByOwner(UserEntity owner);
}
