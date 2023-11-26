package ir.dealit.restful.module.account.controller;

import ir.dealit.restful.api.command.CommandAccountApi;
import ir.dealit.restful.dto.account.ChangeAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommandAccountController implements CommandAccountApi {

    @Override
    public ResponseEntity<Void> updateAccount(ChangeAccount changeAccount, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<Void> addAccount(Authentication authentication) {
        return null;
    }
}
