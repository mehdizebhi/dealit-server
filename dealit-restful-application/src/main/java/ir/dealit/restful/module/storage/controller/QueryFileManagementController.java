package ir.dealit.restful.module.storage.controller;

import ir.dealit.restful.api.query.QueryFileManagementApi;
import ir.dealit.restful.dto.attachment.FileInfo;
import org.bson.types.ObjectId;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryFileManagementController implements QueryFileManagementApi {
    @Override
    public ResponseEntity<PagedModel<FileInfo>> getAllFiles(Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<EntityModel<FileInfo>> getFile(ObjectId id, Authentication authentication) {
        return null;
    }
}
