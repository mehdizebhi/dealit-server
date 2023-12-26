package ir.dealit.restful.module.inbox.controller;

import ir.dealit.restful.api.query.QueryInboxApi;
import ir.dealit.restful.dto.inbox.InboxInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QueryInboxController implements QueryInboxApi {

    @Override
    public ResponseEntity<EntityModel<InboxInfo>> getInboxInfo(Authentication authentication) {
        return null;
    }
}
