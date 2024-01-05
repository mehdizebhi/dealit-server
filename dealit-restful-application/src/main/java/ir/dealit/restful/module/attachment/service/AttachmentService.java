package ir.dealit.restful.module.attachment.service;


import ir.dealit.restful.dto.attachment.Attachment;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface AttachmentService {

    Optional<Attachment> save(Attachment attachment, boolean isPublic);

    default Optional<List<Attachment>> saveAll(List<Attachment> attachments, boolean isPublic) {
        return Optional.of(attachments.stream()
                .map(attachment -> {
                    try {
                        return save(attachment, isPublic);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList()));
    }

    Optional<Attachment> loadById(ObjectId id);

    void delete(Attachment attachment);

    void deleteAll(List<Attachment> attachments);
}
