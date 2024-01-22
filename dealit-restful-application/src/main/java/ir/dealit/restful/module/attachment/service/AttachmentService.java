package ir.dealit.restful.module.attachment.service;


import ir.dealit.restful.dto.attachment.Attachment;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.util.exception.UploadServiceException;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface AttachmentService {

    Optional<Attachment> save(Attachment attachment, boolean isPublic);

    Optional<Attachment> save(Attachment attachment, boolean isPublic, UserEntity owner);

    default Optional<List<Attachment>> saveAll(List<Attachment> attachments, boolean isPublic, UserEntity owner) {
        return Optional.of(attachments.stream()
                .map(attachment -> {
                    try {
                        return save(attachment, isPublic, owner);
                    } catch (Exception e) {
                        throw new UploadServiceException(HttpStatus.SERVICE_UNAVAILABLE);
                    }
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList()));
    }

    Optional<Attachment> loadById(ObjectId id);

    void delete(Attachment attachment);

    void delete(Attachment attachment, UserEntity owner);

    void deleteById(ObjectId attachmentId, UserEntity owner);

    void deleteAll(List<Attachment> attachments, UserEntity owner);
}
