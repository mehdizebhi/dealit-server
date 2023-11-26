package ir.dealit.restful.api.command;

import ir.dealit.restful.dto.account.NewConnection;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/connections")
public interface CommandConnectionApi {

    @PatchMapping("/buy")
    ResponseEntity<Void> buyConnection(
            @RequestBody NewConnection newConnection,
            Authentication authentication
    );
}
