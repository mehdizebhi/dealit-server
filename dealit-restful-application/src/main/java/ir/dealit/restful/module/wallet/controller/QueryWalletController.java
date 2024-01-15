package ir.dealit.restful.module.wallet.controller;

import ir.dealit.restful.api.query.QueryWalletApi;
import ir.dealit.restful.dto.common.ResponseModel;
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
    public ResponseEntity<ResponseModel<WalletInfo>> getWalletInfo(Authentication authentication) {
        return ResponseEntity.ok(
                new ResponseModel.Builder<WalletInfo>()
                        .data(walletService.walletInfo((UserEntity) authentication.getPrincipal()))
                        .success()
                        .build()
        );
    }

    @Override
    public ResponseEntity<ResponseModel<CreditCardInfo>> getCreditCardInfo(Authentication authentication) {
        return ResponseEntity.ok(
                new ResponseModel.Builder<CreditCardInfo>()
                        .data(walletService.creditCard((UserEntity) authentication.getPrincipal()))
                        .success()
                        .build()
        );
    }
}
