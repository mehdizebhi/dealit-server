package ir.dealit.restful.api.root;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public interface InitialApi {

    @GetMapping("")
    ResponseEntity<String> init();

}
