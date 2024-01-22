package ir.dealit.restful.module.attachment.repository;

import ir.dealit.restful.module.attachment.entity.AttachmentEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttachmentRepository extends MongoRepository<AttachmentEntity, ObjectId> {

    Optional<AttachmentEntity> findByFileId(String fileId);

    Optional<AttachmentEntity> findByUri(String uri);

    Optional<AttachmentEntity> findByIdAndOwner(ObjectId id, UserEntity owner);

    Page<AttachmentEntity> findByOwner(UserEntity owner, Pageable pageable);
}
