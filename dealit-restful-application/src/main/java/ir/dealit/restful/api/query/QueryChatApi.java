package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.chat.Conversation;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/chats")
public interface QueryChatApi {

    @GetMapping("/")
    ResponseEntity<?> getChat(
            Authentication authentication
    );

    @GetMapping("/conversations/")
    ResponseEntity<PagedModel<Conversation>> getAllConversation(
            @PageableDefault Pageable pageable,
            Authentication authentication
    );

    @GetMapping("/conversations/{id}")
    ResponseEntity<EntityModel<Conversation>> getConversation(
            @PathVariable("id") ObjectId id,
            Authentication authentication
    );
}
