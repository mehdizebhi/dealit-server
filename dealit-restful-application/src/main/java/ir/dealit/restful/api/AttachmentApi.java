package ir.dealit.restful.api;

import ir.dealit.restful.dto.attachment.Attachment;
import org.bson.types.ObjectId;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/v1/attachments")
public interface AttachmentApi {

    @PostMapping("/pub-upload")
    ResponseEntity<Attachment> publicUpload(
            @RequestPart MultipartFile file
    ) throws Exception;

    @PostMapping("/pub-upload-all")
    ResponseEntity<List<Attachment>> publicUploadAll(
            @RequestPart List<MultipartFile> files
    );

    @PostMapping("/upload")
    ResponseEntity<Attachment> privateUpload(
            @RequestPart MultipartFile file
    ) throws Exception;

    @PostMapping("/upload-all")
    ResponseEntity<List<Attachment>> privateUploadAll(
            @RequestPart List<MultipartFile> files
    );

    @GetMapping("/download/{id}")
    ResponseEntity<Resource> download(
            @PathVariable("id") ObjectId id
    );

    @GetMapping("/{id}")
    ResponseEntity<Attachment> getAttachmentInfo(
            @PathVariable("id") ObjectId id
    );
}
