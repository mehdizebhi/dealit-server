package ir.dealit.restful.controller.v1.api;

import ir.dealit.restful.dto.attachment.Attachment;
import org.bson.types.ObjectId;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/v1/attachments")
public interface AttachmentApi {

    @PostMapping("/upload")
    ResponseEntity<Attachment> upload(
            @RequestPart MultipartFile file
    ) throws Exception;

    @PostMapping("/upload-all")
    ResponseEntity<List<Attachment>> uploadAll(
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
