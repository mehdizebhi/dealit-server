package ir.dealit.restful.module.wallet.controller;

import ir.dealit.restful.api.query.QueryWalletApi;
import ir.dealit.restful.dto.wallet.WalletInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
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
}
