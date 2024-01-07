package ir.dealit.restful.module.job.repository;

import ir.dealit.restful.module.job.entity.SkillEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SkillRepository extends MongoRepository<SkillEntity, ObjectId> {

    Optional<SkillEntity> findFirstByTitle(String title);
}
