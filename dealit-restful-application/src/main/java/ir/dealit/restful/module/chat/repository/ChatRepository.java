package ir.dealit.restful.module.chat.repository;

import ir.dealit.restful.module.chat.entity.ChatEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends MongoRepository<ChatEntity, ObjectId> {
}
