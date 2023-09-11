package ir.dealit.restful.repository;

import ir.dealit.restful.repository.entity.ChatEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends MongoRepository<ChatEntity, ObjectId> {
}
