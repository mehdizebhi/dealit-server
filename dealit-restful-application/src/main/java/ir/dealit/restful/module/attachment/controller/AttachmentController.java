package ir.dealit.restful.module.attachment.controller;

import ir.dealit.restful.api.AttachmentApi;
import ir.dealit.restful.dto.attachment.Attachment;
import ir.dealit.restful.module.attachment.service.AttachmentService;
import ir.dealit.restful.module.attachment.service.AttachmentDaoService;
import ir.dealit.restful.util.hateoas.assembler.AttachmentModelAssembler;
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
    public ResponseEntity<Attachment> upload(MultipartFile file) throws Exception {
        return attachmentService.save(assembler.multipartFileToModel(file))
                .map(ResponseEntity::ok)
                .orElse(status(HttpStatus.UNAUTHORIZED).build());
    }

    @Override
    public ResponseEntity<List<Attachment>> uploadAll(List<MultipartFile> files) {
        var attachments = files.stream()
                .map(file -> {
                    try {
                        return attachmentService.save(assembler.multipartFileToModel(file)).get();
                    } catch (Exception e) {
                        throw new RuntimeException("Unable to upload files.", e);
                    }
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(attachments);
    }

    @Override
    public ResponseEntity<Resource> download(ObjectId id) {
        Attachment attachment = attachmentService.loadById(id).get();
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, attachment.getFileType())
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(attachment.getFileSize()))
                .header(HttpHeaders.CONTENT_DISPOSITION,  "attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }

    @Override
    public ResponseEntity<Attachment> getAttachmentInfo(ObjectId id) {
        return daoService.findById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }
}
