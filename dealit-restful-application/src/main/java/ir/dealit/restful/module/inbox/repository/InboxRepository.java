package ir.dealit.restful.module.inbox.repository;

import ir.dealit.restful.module.inbox.entity.InboxEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InboxRepository extends MongoRepository<InboxEntity, ObjectId> {
}
