package ir.dealit.restful.module.attachment.repository;

import ir.dealit.restful.module.attachment.entity.AttachmentEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttachmentRepository extends MongoRepository<AttachmentEntity, ObjectId> {

    Optional<AttachmentEntity> findByFileId(String fileId);
}
