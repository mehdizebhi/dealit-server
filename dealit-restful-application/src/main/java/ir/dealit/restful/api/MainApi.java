package ir.dealit.restful.api;

import ir.dealit.restful.dto.common.InitialSource;
import ir.dealit.restful.dto.common.ResponseModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("")
public interface MainApi {

    @GetMapping({"", "/"})
    ResponseEntity<ResponseModel<Map<String, Object>>> getInitialSource(
            Authentication authentication
    );

    /*@GetMapping("")
    ResponseEntity<String> init();

    @GetMapping("/test")
    ResponseEntity<Page<ChatMessage>> test();*/

}
