package ir.dealit.restful.service.arvan;

import ir.dealit.restful.dto.attachment.Attachment;
import ir.dealit.restful.service.attachment.AttachmentService;
import ir.dealit.restful.service.dao.AttachmentDaoService;
import ir.dealit.restful.util.hateoas.assembler.AttachmentModelAssembler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.util.Optional;

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
    public Optional<Attachment> save(Attachment attachment) throws Exception {
        try {
            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(defaultBucketName)
                    .key(attachment.getFileId() + "." + attachment.getFileName().split("\\.")[1])
                    .build();

            s3.putObject(putOb, RequestBody.fromBytes(attachment.getData()));
            return attachmentDaoService.register(attachment).map(assembler::toModel);

        } catch (S3Exception e) {
            log.error("S3 has problem with uploading = {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Attachment> loadById(ObjectId id) {
        try {
            var entity = attachmentDaoService.findById(id).get();
            GetObjectRequest objectRequest = GetObjectRequest.builder()
                    .key(entity.getFileId() + "." + entity.getFileName().split("\\.")[1])
                    .bucket(defaultBucketName)
                    .build();

            ResponseBytes<GetObjectResponse> objectBytes = s3.getObjectAsBytes(objectRequest);
            Attachment attachment = assembler.toModel(entity);
            attachment.setData(objectBytes.asByteArray());
            return Optional.of(attachment);
        } catch (S3Exception e) {
            log.error("S3 has problem with downloading fileId = {}", id);
            return Optional.empty();
        }
    }

}
