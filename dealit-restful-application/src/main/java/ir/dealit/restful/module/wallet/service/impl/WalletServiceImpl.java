package ir.dealit.restful.module.wallet.service.impl;

import ir.dealit.restful.dto.wallet.Asset;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.service.UserAuthService;
import ir.dealit.restful.module.wallet.entity.AssetEntity;
import ir.dealit.restful.module.wallet.repository.WalletRepository;
import ir.dealit.restful.module.wallet.service.WalletService;
import ir.dealit.restful.dto.enums.Currency;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final UserAuthService userAuthService;
    private final WalletRepository walletRepository;

    @Override
    public Optional<?> allAssets(UserEntity user) {
        return Optional.empty();
    }

    @Override
    public Optional<?> asset(Currency currency, UserEntity user) {
        return Optional.empty();
    }

    @Override
    public double totalBalance(UserEntity user) {
        var walletId = userAuthService.getWalletId(user.getId());
        if (walletId.isPresent()) {
            var wallet = walletRepository.findById(walletId.get());
            return getTotalBalanceInTOMAN(wallet.get().getAssets()).doubleValue();
        }
        return 0d;
    }

    @Override
    public void addAsset(Asset newAsset, UserEntity user) {

    }

    @Override
    public void updateAsset(Asset asset, UserEntity user) {

    }

    @Override
    public void transfer(Asset asset, ObjectId toAccountId, UserEntity user) {

    }

    private BigDecimal getTotalBalanceInTOMAN(List<AssetEntity> assets) {
        BigDecimal total = BigDecimal.ZERO;
        for (var asset : assets) {
            total.add(asset.getBalance().multiply(convertToTOMAN(asset.getCurrency())));
        }
        return total;
    }

    private BigDecimal convertToTOMAN (Currency from) {
        return switch (from) {
            case USD -> new BigDecimal(50_000);
            case RIAL -> new BigDecimal(10);
            default -> BigDecimal.ONE;
        };
    }
}
