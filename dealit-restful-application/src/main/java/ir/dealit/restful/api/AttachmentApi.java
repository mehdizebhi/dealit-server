package ir.dealit.restful.api;

import ir.dealit.restful.dto.attachment.Attachment;
import ir.dealit.restful.dto.common.ResponseModel;
import org.bson.types.ObjectId;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/v1/attachments")
public interface AttachmentApi {

    @PostMapping("/pub-upload")
    ResponseEntity<ResponseModel<Attachment>> publicUpload(
            @RequestPart MultipartFile file,
            Authentication authentication
    ) throws Exception;

    @PostMapping("/pub-upload-all")
    ResponseEntity<ResponseModel<List<Attachment>>> publicUploadAll(
            @RequestPart List<MultipartFile> files,
            Authentication authentication
    );

    @PostMapping("/upload")
    ResponseEntity<ResponseModel<Attachment>> privateUpload(
            @RequestPart MultipartFile file,
            Authentication authentication
    ) throws Exception;

    @PostMapping("/upload-all")
    ResponseEntity<ResponseModel<List<Attachment>>> privateUploadAll(
            @RequestPart List<MultipartFile> files,
            Authentication authentication
    );

    @GetMapping("/download/{id}")
    ResponseEntity<Resource> download(
            @PathVariable("id") ObjectId id
    );

    @GetMapping("/{id}")
    ResponseEntity<ResponseModel<Attachment>> getAttachmentInfo(
            @PathVariable("id") ObjectId id
    );

    @GetMapping("")
    ResponseEntity<ResponseModel<List<Attachment>>> getAttachments(
            @PageableDefault(sort = {"updatedAt"}, direction = Sort.Direction.DESC) Pageable pageable,
            Authentication authentication
    );

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseModel<Void>> deleteAttachment(
            @PathVariable("id") ObjectId id,
            Authentication authentication
    );
}
