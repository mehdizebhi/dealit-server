package ir.dealit.restful.module.job.repository;

import ir.dealit.restful.module.job.entity.FieldEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FieldRepository extends MongoRepository<FieldEntity, ObjectId> {

    Optional<FieldEntity> findFirstByTitle(String title);
}
