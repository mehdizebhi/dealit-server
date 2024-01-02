package ir.dealit.restful.api.command;

import ir.dealit.restful.dto.wallet.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/wallets")
public interface CommandWalletApi {

    @PostMapping("/checkouts")
    ResponseEntity<Void> createCheckout(
            @RequestBody NewCheckout newCheckout,
            Authentication authentication
    );

    @PatchMapping("/checkouts")
    ResponseEntity<Void> updateCheckout(
            @RequestBody ChangeCheckout changeCheckout,
            Authentication authentication
    );

    @PostMapping("/funds")
    ResponseEntity<Void> createFunds(
            @RequestBody NewFund newFund,
            Authentication authentication
    );

    @PatchMapping("/funds")
    ResponseEntity<Void> updateFunds(
            @RequestBody ChangeFund changeFund,
            Authentication authentication
    );

    @PostMapping("/credit-card")
    ResponseEntity<Void> addCreditCard(
            @RequestBody @Valid NewCreditCard newCreditCard,
            Authentication authentication
    );
}
