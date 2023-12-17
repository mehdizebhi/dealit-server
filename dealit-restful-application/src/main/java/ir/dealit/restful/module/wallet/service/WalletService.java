package ir.dealit.restful.module.wallet.service;

import ir.dealit.restful.dto.wallet.Asset;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import ir.dealit.restful.dto.enums.Currency;

import java.util.Optional;

@Secured("ROLE_USER")
public interface WalletService {
    //--------------------------------------
    // Fetching Data
    //--------------------------------------
    Optional<?> allAssets(UserEntity user);

    Optional<?> asset(Currency currency, UserEntity user);

    double totalBalance(UserEntity user);

    //--------------------------------------
    // Manipulate Data
    //--------------------------------------
    void addAsset(Asset newAsset, UserEntity user);

    void updateAsset(Asset asset, UserEntity user);

    void transfer(Asset asset, ObjectId toAccountId, UserEntity user);
}
