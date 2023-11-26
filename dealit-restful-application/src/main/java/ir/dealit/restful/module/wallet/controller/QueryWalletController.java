package ir.dealit.restful.module.wallet.controller;

import ir.dealit.restful.api.query.QueryWalletApi;
import ir.dealit.restful.dto.wallet.Checkout;
import ir.dealit.restful.dto.wallet.Fund;
import ir.dealit.restful.dto.wallet.WalletInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QueryWalletController implements QueryWalletApi {
    @Override
    public ResponseEntity<EntityModel<WalletInfo>> getWalletInfo(Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<PagedModel<Checkout>> getCheckouts(Pageable pageable, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<PagedModel<Fund>> getFunds(Pageable pageable, Authentication authentication) {
        return null;
    }
}
