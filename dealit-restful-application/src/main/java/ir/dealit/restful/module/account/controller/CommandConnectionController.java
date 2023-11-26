package ir.dealit.restful.module.account.controller;

import ir.dealit.restful.api.command.CommandConnectionApi;
import ir.dealit.restful.dto.account.NewConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommandConnectionController implements CommandConnectionApi {
    @Override
    public ResponseEntity<Void> buyConnection(NewConnection newConnection, Authentication authentication) {
        return null;
    }
}
