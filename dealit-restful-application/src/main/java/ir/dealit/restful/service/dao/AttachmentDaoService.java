package ir.dealit.restful.service.dao;

import ir.dealit.restful.dto.attachment.Attachment;
import ir.dealit.restful.repository.AttachmentRepository;
import ir.dealit.restful.repository.entity.AttachmentEntity;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachmentDaoService {

    private final AttachmentRepository repository;

    public Optional<AttachmentEntity> register(Attachment attachment) {
        AttachmentEntity entity = repository.save(toEntity(attachment));
        return Optional.of(entity);
    }

    public Optional<AttachmentEntity> findByFileId(String fileId) {
        return repository.findByFileId(fileId);
    }

    public Optional<AttachmentEntity> findById(ObjectId id) {
        return repository.findById(id);
    }


    private AttachmentEntity toEntity(Attachment attachment) {
        AttachmentEntity entity = AttachmentEntity.builder().build();
        BeanUtils.copyProperties(attachment, entity);
        return entity;
    }

}
