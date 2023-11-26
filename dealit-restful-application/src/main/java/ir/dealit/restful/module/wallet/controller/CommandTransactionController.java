package ir.dealit.restful.module.wallet.controller;

import ir.dealit.restful.api.command.CommandTransactionApi;
import ir.dealit.restful.dto.transaction.NewTransaction;
import ir.dealit.restful.dto.transaction.VerifyTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommandTransactionController implements CommandTransactionApi {
    @Override
    public ResponseEntity<Void> createTransaction(NewTransaction newTransaction, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<Void> verifyTransaction(VerifyTransaction verifyTransaction, Authentication authentication) {
        return null;
    }
}
