package ir.dealit.restful.controller.v1;

import ir.dealit.restful.controller.v1.api.AttachmentApi;
import ir.dealit.restful.dto.attachment.Attachment;
import ir.dealit.restful.service.attachment.AttachmentService;
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

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AttachmentController implements AttachmentApi {


    private final AttachmentService attachmentService;
    private final AttachmentModelAssembler assembler;


    @Override
    public ResponseEntity<Attachment> upload(MultipartFile file) throws Exception {
        return attachmentService.save(assembler.multipartFileToModel(file))
                .map(ResponseEntity::ok)
                .orElse(status(HttpStatus.UNAUTHORIZED).build());
    }

    @Override
    public ResponseEntity<List<Attachment>> uploadAll(List<MultipartFile> files) {
        files.forEach(file -> log.info("file = {}", file.getName() + " " + file.getContentType()));
        return null;
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
}
