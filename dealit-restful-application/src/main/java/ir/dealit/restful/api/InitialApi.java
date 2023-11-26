package ir.dealit.restful.api;

import ir.dealit.restful.dto.chat.ChatMessage;
import ir.dealit.restful.dto.common.InitialSource;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1")
public interface InitialApi {

    @GetMapping("")
    ResponseEntity<EntityModel<InitialSource>> getInitialSource(
            Authentication authentication
    );

    /*@GetMapping("")
    ResponseEntity<String> init();

    @GetMapping("/test")
    ResponseEntity<Page<ChatMessage>> test();*/

}
