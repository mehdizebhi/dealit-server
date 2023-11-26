package ir.dealit.restful.api.command;

import ir.dealit.restful.dto.transaction.NewTransaction;
import ir.dealit.restful.dto.transaction.VerifyTransaction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/transactions")
public interface CommandTransactionApi {

    @PostMapping("/")
    ResponseEntity<Void> createTransaction(
            @RequestBody NewTransaction newTransaction,
            Authentication authentication
    );

    @PostMapping("/verify")
    ResponseEntity<Void> verifyTransaction(
            @RequestBody VerifyTransaction verifyTransaction,
            Authentication authentication
    );
}
