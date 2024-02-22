package ir.dealit.restful.module.user.repository.impl;

import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.repository.CustomUserModuleRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomUserModuleRepositoryImpl implements CustomUserModuleRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public void updateConnections(ObjectId userId, int newConnections) {
        Query query = new Query(Criteria.where("_id").is(userId));
        Update update = new Update().set("connections", newConnections);

        mongoTemplate.updateFirst(query, update, UserEntity.class);
    }
}
