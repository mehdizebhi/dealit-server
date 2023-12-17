package ir.dealit.restful.module.wallet.service.impl;

import ir.dealit.restful.dto.enums.Currency;
import ir.dealit.restful.module.wallet.entity.AssetEntity;
import ir.dealit.restful.module.wallet.service.AssetService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AssetServiceImpl implements AssetService {
    @Override
    public BigDecimal convert(AssetEntity asset, Currency target) {
        return switch (target) {
            case USD  -> toUSD(asset);
            case RIAL -> toRIAL(asset);
            case TOMAN -> toTOMAN(asset);
            default -> throw new RuntimeException(String.format("The Currency: %s is not exist!", target));
        };
    }

    private BigDecimal toUSD(AssetEntity asset) {
        return switch (asset.getCurrency()) {
            case USD  -> asset.getBalance();
            case RIAL -> asset.getBalance().divide(new BigDecimal(500_000));
            case TOMAN -> asset.getBalance().divide(new BigDecimal(50_000));
            default -> throw new RuntimeException(String.format("The Currency: %s is not exist!", asset.getCurrency()));
        };
    }

    private BigDecimal toRIAL(AssetEntity asset) {
        return switch (asset.getCurrency()) {
            case USD  -> asset.getBalance().multiply(new BigDecimal(500_000));
            case RIAL -> asset.getBalance();
            case TOMAN -> asset.getBalance().multiply(new BigDecimal(10));
            default -> throw new RuntimeException(String.format("The Currency: %s is not exist!", asset.getCurrency()));
        };
    }

    private BigDecimal toTOMAN(AssetEntity asset) {
        return switch (asset.getCurrency()) {
            case USD  -> asset.getBalance().multiply(new BigDecimal(50_000));
            case RIAL -> asset.getBalance().divide(new BigDecimal(10));
            case TOMAN -> asset.getBalance();
            default -> throw new RuntimeException(String.format("The Currency: %s is not exist!", asset.getCurrency()));
        };
    }
}
