package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.wallet.Checkout;
import ir.dealit.restful.dto.wallet.Fund;
import ir.dealit.restful.dto.wallet.PaymentHistory;
import ir.dealit.restful.dto.wallet.WalletInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/wallets")
public interface QueryWalletApi {

    @GetMapping("/")
    ResponseEntity<EntityModel<WalletInfo>> getWalletInfo(
            Authentication authentication
    );

    @GetMapping("/checkouts")
    ResponseEntity<PagedModel<Checkout>> getCheckouts(
            @PageableDefault Pageable pageable,
            Authentication authentication
    );

    @GetMapping("/funds")
    ResponseEntity<PagedModel<Fund>> getFunds(
            @PageableDefault Pageable pageable,
            Authentication authentication
    );
}
