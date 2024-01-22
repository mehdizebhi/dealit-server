package ir.dealit.restful.module.attachment.service;

import ir.dealit.restful.dto.attachment.Attachment;
import ir.dealit.restful.module.attachment.repository.AttachmentRepository;
import ir.dealit.restful.module.attachment.entity.AttachmentEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.util.exception.NotDeletableException;
import ir.dealit.restful.util.exception.NotFoundResourceException;
import ir.dealit.restful.util.hateoas.AttachmentModelAssembler;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttachmentDaoService {

    private final AttachmentRepository repository;
    private final AttachmentModelAssembler assembler;

    public Optional<AttachmentEntity> register(Attachment attachment) {
        AttachmentEntity entity = repository.save(toEntity(attachment));
        return Optional.of(entity);
    }

    public Optional<AttachmentEntity> register(Attachment attachment, UserEntity owner) {
        var attachmentEntity = toEntity(attachment);
        attachmentEntity.setOwner(owner);
        AttachmentEntity entity = repository.save(attachmentEntity);
        return Optional.of(entity);
    }

    public Optional<AttachmentEntity> findByFileId(String fileId) {
        return repository.findByFileId(fileId);
    }

    public Optional<AttachmentEntity> findById(ObjectId id) {
        return repository.findById(id);
    }

    public Optional<AttachmentEntity> attachment(ObjectId id, UserEntity owner) {
        return repository.findByIdAndOwner(id, owner);
    }

    public Page<Attachment> attachments(UserEntity owner, Pageable pageable) {
        return repository.findByOwner(owner, pageable).map(assembler::toModel);
    }

    public void delete(ObjectId id) {
        repository.deleteById(id);
    }

    public void delete(ObjectId id, UserEntity owner) {
        var entityOp = repository.findByIdAndOwner(id, owner);
        if (!entityOp.isPresent()) {
            throw new NotFoundResourceException("attachment is not found");
        }
        repository.deleteById(id);
    }

    public void deleteAll(List<ObjectId> ids){
        repository.deleteAllById(ids);
    }

    public void deleteAll(List<ObjectId> ids, UserEntity owner){
        repository.deleteAllById(ids.stream()
                .filter(id -> repository.findByIdAndOwner(id, owner).isPresent()).collect(Collectors.toList()));
    }

    private AttachmentEntity toEntity(Attachment attachment) {
        AttachmentEntity entity = AttachmentEntity.builder().build();
        BeanUtils.copyProperties(attachment, entity);
        return entity;
    }
}
