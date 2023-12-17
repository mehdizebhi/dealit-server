package ir.dealit.restful.module.chat.repository;

import ir.dealit.restful.module.chat.entity.ConversationEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends MongoRepository<ConversationEntity, ObjectId> {

    @Query(value = "{'chat': ?0, 'messages': {'seen': false}}", count = true)
    Integer countNewMessageByChat(ObjectId chatId);
}
