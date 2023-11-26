package ir.dealit.restful.module.user.repository;

import ir.dealit.restful.module.user.entity.RoleEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<RoleEntity, ObjectId> {

    Integer countByName(String name);
    RoleEntity findByName(String name);
}
