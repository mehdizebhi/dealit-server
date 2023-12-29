package ir.dealit.restful.module.wallet.controller;

import ir.dealit.restful.api.command.CommandWalletApi;
import ir.dealit.restful.dto.wallet.*;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommandWalletController implements CommandWalletApi {

    private final WalletService walletService;

    @Override
    public ResponseEntity<Void> createCheckout(NewCheckout newCheckout, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateCheckout(ChangeCheckout changeCheckout, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<Void> createFunds(NewFund newFund, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateFunds(ChangeFund changeFund, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<Void> addCreditCard(NewCreditCard newCreditCard, Authentication authentication) {
        walletService.newCreditCard(newCreditCard, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.status(201).build();
    }
}
