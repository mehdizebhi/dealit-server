package ir.dealit.restful.module.wallet.service;

import ir.dealit.restful.dto.wallet.Asset;
import org.bson.types.ObjectId;
import org.springframework.security.core.Authentication;

import java.util.Currency;
import java.util.Optional;

public interface WalletService {
    //--------------------------------------
    // Fetching Data
    //--------------------------------------
    Optional<?> allAssets(Authentication authentication);

    Optional<?> asset(Currency currency, Authentication authentication);

    Optional<?> totalAmount(Authentication authentication);

    //--------------------------------------
    // Manipulate Data
    //--------------------------------------
    void addAsset(Asset newAsset, Authentication authentication);

    void updateAsset(Asset asset, Authentication authentication);

    void transfer(Asset asset, ObjectId toAccountId, Authentication authentication);
}
