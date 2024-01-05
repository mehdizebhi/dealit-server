package ir.dealit.restful.module.attachment.service;

import ir.dealit.restful.dto.attachment.Attachment;
import ir.dealit.restful.util.exception.UploadServiceException;
import ir.dealit.restful.util.hateoas.AttachmentModelAssembler;
import ir.dealit.restful.util.helper.AttachmentHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArvanCloudStorageService implements AttachmentService {

    @Value("${arvan.s3.bucket_name}")
    private String defaultBucketName;

    private final S3Client s3;
    private final AttachmentDaoService attachmentDaoService;
    private final AttachmentModelAssembler assembler;

    @Override
    public Optional<Attachment> save(Attachment attachment, boolean isPublic) {
        try {
            var putObjectBuilder = PutObjectRequest.builder()
                    .bucket(defaultBucketName)
                    .key(AttachmentHelper.getKey(attachment));
            if (isPublic) {
                putObjectBuilder.acl(ObjectCannedACL.PUBLIC_READ);
            } else {
                putObjectBuilder.acl(ObjectCannedACL.PRIVATE);
            }
            PutObjectRequest putOb = putObjectBuilder.build();

            s3.putObject(putOb, RequestBody.fromBytes(attachment.getData()));
            log.info("File Uploaded!");
            return attachmentDaoService.register(attachment).map(assembler::toModel);

        } catch (S3Exception e) {
            log.error("S3 has problem with uploading = {}", e.getMessage());
            throw new UploadServiceException(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    public Optional<Attachment> loadById(ObjectId id) {
        try {
            var entity = attachmentDaoService.findById(id).get();
            GetObjectRequest objectRequest = GetObjectRequest.builder()
                    .key(AttachmentHelper.getKey(entity))
                    .bucket(defaultBucketName)
                    .build();

            ResponseBytes<GetObjectResponse> objectBytes = s3.getObjectAsBytes(objectRequest);
            Attachment attachment = assembler.toModel(entity);
            attachment.setData(objectBytes.asByteArray());
            return Optional.of(attachment);
        } catch (S3Exception e) {
            log.error("S3 Client has problem with downloading fileId = {}", id);
            throw new UploadServiceException(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    @Transactional
    public void delete(Attachment attachment) {
        try {
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(defaultBucketName)
                    .key(AttachmentHelper.getKey(attachment))
                    .build();

            s3.deleteObject(deleteObjectRequest);
            attachmentDaoService.delete(new ObjectId(attachment.getId()));
        } catch (S3Exception e) {
            log.error("S3 Client Can not delete object = {}", AttachmentHelper.getKey(attachment));
            throw new UploadServiceException(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    @Transactional
    public void deleteAll(List<Attachment> attachments) {
        List<ObjectIdentifier> keys = attachments.stream()
                .map(attachment -> ObjectIdentifier.builder().key(AttachmentHelper.getKey(attachment)).build())
                .collect(Collectors.toList());

        List<ObjectId> ids = attachments.stream()
                .map(attachment -> new ObjectId(attachment.getId()))
                .collect(Collectors.toList());

        Delete delete = Delete.builder()
                .objects(keys)
                .build();
        try {
            DeleteObjectsRequest  multiObjectDeleteRequest = DeleteObjectsRequest.builder()
                    .bucket(defaultBucketName)
                    .delete(delete)
                    .build();

            s3.deleteObjects(multiObjectDeleteRequest);
            attachmentDaoService.deleteAll(ids);
        } catch (S3Exception e) {
            log.error("S3 Client Can not delete objects = {}", keys);
            throw new UploadServiceException(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
