package ir.dealit.restful.api.command;

import ir.dealit.restful.dto.account.ChangeAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/accounts")
public interface CommandAccountApi {

    @PutMapping("/")
    ResponseEntity<Void> updateAccount(
            @RequestBody ChangeAccount changeAccountReq,
            Authentication authentication
    );

    @PatchMapping("/add")
    ResponseEntity<Void> addAccount(
            Authentication authentication
    );
}
