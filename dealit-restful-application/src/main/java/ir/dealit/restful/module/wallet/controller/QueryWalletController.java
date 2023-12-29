package ir.dealit.restful.module.wallet.controller;

import ir.dealit.restful.api.query.QueryWalletApi;
import ir.dealit.restful.dto.wallet.CreditCardInfo;
import ir.dealit.restful.dto.wallet.NewCreditCard;
import ir.dealit.restful.dto.wallet.WalletInfo;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QueryWalletController implements QueryWalletApi {

    private final WalletService walletService;

    @Override
    public ResponseEntity<EntityModel<WalletInfo>> getWalletInfo(Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<EntityModel<CreditCardInfo>> getCreditCardInfo(Authentication authentication) {
        return ResponseEntity.ok(EntityModel.of(
                walletService.creditCard((UserEntity) authentication.getPrincipal())
        ));
    }
}
