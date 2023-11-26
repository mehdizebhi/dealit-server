package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.attachment.FileInfo;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/files")
public interface QueryFileManagementApi {

    @GetMapping("/")
    ResponseEntity<PagedModel<FileInfo>> getAllFiles(
            Authentication authentication
    );

    @GetMapping("/{id}")
    ResponseEntity<EntityModel<FileInfo>> getFile(
            @PathVariable("id") ObjectId id,
            Authentication authentication
    );
}
