package ir.dealit.restful.module.wallet.service.impl;

import ir.dealit.restful.dto.wallet.Asset;
import ir.dealit.restful.module.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    @Override
    public Optional<?> allAssets(Authentication authentication) {
        return Optional.empty();
    }

    @Override
    public Optional<?> asset(Currency currency, Authentication authentication) {
        return Optional.empty();
    }

    @Override
    public Optional<?> totalAmount(Authentication authentication) {
        return Optional.empty();
    }

    @Override
    public void addAsset(Asset newAsset, Authentication authentication) {

    }

    @Override
    public void updateAsset(Asset asset, Authentication authentication) {

    }

    @Override
    public void transfer(Asset asset, ObjectId toAccountId, Authentication authentication) {

    }
}
