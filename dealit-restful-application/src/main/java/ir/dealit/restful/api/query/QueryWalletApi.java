package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.wallet.CreditCardInfo;
import ir.dealit.restful.dto.wallet.WalletInfo;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/wallets")
public interface QueryWalletApi {

    @GetMapping("/info")
    ResponseEntity<EntityModel<WalletInfo>> getWalletInfo(
            Authentication authentication
    );

    @GetMapping("/credit-card")
    ResponseEntity<EntityModel<CreditCardInfo>> getCreditCardInfo(
            Authentication authentication
    );
}
