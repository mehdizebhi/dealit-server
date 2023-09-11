package ir.dealit.restful.web.controller.v1.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public interface InitialApi {

    @GetMapping("")
    ResponseEntity<String> init();

}
