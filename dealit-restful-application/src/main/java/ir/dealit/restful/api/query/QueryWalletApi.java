package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.wallet.CreditCardInfo;
import ir.dealit.restful.dto.wallet.WalletInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/wallets")
public interface QueryWalletApi {

    @GetMapping("/info")
    ResponseEntity<ResponseModel<WalletInfo>> getWalletInfo(
            Authentication authentication
    );

    @GetMapping("/credit-card")
    ResponseEntity<ResponseModel<CreditCardInfo>> getCreditCardInfo(
            Authentication authentication
    );
}
