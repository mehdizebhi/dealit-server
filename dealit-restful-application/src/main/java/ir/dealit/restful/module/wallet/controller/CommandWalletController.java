package ir.dealit.restful.module.wallet.controller;

import ir.dealit.restful.api.command.CommandWalletApi;
import ir.dealit.restful.dto.wallet.ChangeCheckout;
import ir.dealit.restful.dto.wallet.ChangeFund;
import ir.dealit.restful.dto.wallet.NewCheckout;
import ir.dealit.restful.dto.wallet.NewFund;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommandWalletController implements CommandWalletApi {
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
}
