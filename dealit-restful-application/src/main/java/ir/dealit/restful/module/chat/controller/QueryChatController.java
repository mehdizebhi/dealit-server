package ir.dealit.restful.module.chat.controller;

import ir.dealit.restful.api.query.QueryChatApi;
import ir.dealit.restful.dto.chat.Conversation;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryChatController implements QueryChatApi {
    @Override
    public ResponseEntity<?> getChat(Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<PagedModel<Conversation>> getAllConversation(Pageable pageable, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<EntityModel<Conversation>> getConversation(ObjectId id, Authentication authentication) {
        return null;
    }
}
