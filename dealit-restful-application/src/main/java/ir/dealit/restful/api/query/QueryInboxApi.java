package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.inbox.InboxInfo;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/inbox")
public interface QueryInboxApi {

    @GetMapping("/info")
    ResponseEntity<EntityModel<InboxInfo>> getInboxInfo(
            Authentication authentication
    );
}
