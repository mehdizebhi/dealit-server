package ir.dealit.restful.module.attachment.controller;

import ir.dealit.restful.api.AttachmentApi;
import ir.dealit.restful.dto.attachment.Attachment;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.module.attachment.service.AttachmentService;
import ir.dealit.restful.module.attachment.service.AttachmentDaoService;
import ir.dealit.restful.util.exception.InvalidFileException;
import ir.dealit.restful.util.exception.UploadServiceException;
import ir.dealit.restful.util.hateoas.AttachmentModelAssembler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AttachmentController implements AttachmentApi {

    private final AttachmentService attachmentService;
    private final AttachmentDaoService daoService;
    private final AttachmentModelAssembler assembler;

    @Override
    public ResponseEntity<ResponseModel<Attachment>> publicUpload(MultipartFile file) throws Exception {
        var attachment = attachmentService.save(assembler.multipartFileToModel(file), true);
        if (attachment.isPresent()) {
            return ResponseEntity.ok(new ResponseModel.Builder<Attachment>().success().data(attachment.get()).build());
        }
        throw new UploadServiceException(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    public ResponseEntity<ResponseModel<List<Attachment>>> publicUploadAll(List<MultipartFile> files) {
        var attachments = files.stream()
                .map(file -> {
                    try {
                        return attachmentService.save(assembler.multipartFileToModel(file), true).get();
                    } catch (Exception e) {
                        throw new UploadServiceException(HttpStatus.SERVICE_UNAVAILABLE);
                    }
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseModel.Builder<List<Attachment>>().success().data(attachments).build());
    }

    @Override
    public ResponseEntity<ResponseModel<Attachment>> privateUpload(MultipartFile file) throws Exception {
        var attachment = attachmentService.save(assembler.multipartFileToModel(file), false);
        if (attachment.isPresent()) {
            return ResponseEntity.ok(new ResponseModel.Builder<Attachment>().success().data(attachment.get()).build());
        }
        throw new UploadServiceException(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    public ResponseEntity<ResponseModel<List<Attachment>>> privateUploadAll(List<MultipartFile> files) {
        var attachments = files.stream()
                .map(file -> {
                    try {
                        return attachmentService.save(assembler.multipartFileToModel(file), false).get();
                    } catch (Exception e) {
                        throw new UploadServiceException(HttpStatus.SERVICE_UNAVAILABLE);
                    }
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseModel.Builder<List<Attachment>>().success().data(attachments).build());
    }

    @Override
    public ResponseEntity<Resource> download(ObjectId id) {
        Attachment attachment = attachmentService.loadById(id).get();
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, attachment.getFileType())
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(attachment.getFileSize()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }

    @Override
    public ResponseEntity<ResponseModel<Attachment>> getAttachmentInfo(ObjectId id) {
        var attachment = daoService.findById(id);
        if (attachment.isPresent()) {
            return ResponseEntity.ok(new ResponseModel.Builder<Attachment>()
                    .data(assembler.toModel(attachment.get()))
                    .success()
                    .build());
        }
        throw new InvalidFileException(HttpStatus.NOT_FOUND);
    }
}
