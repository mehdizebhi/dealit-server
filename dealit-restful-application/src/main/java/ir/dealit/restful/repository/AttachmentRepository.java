package ir.dealit.restful.repository;

import ir.dealit.restful.repository.entity.AttachmentEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttachmentRepository extends MongoRepository<AttachmentEntity, ObjectId> {

    Optional<AttachmentEntity> findByFileId(String fileId);
}
