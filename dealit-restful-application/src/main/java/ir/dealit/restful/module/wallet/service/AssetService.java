package ir.dealit.restful.module.wallet.service;

import ir.dealit.restful.dto.enums.Currency;
import ir.dealit.restful.module.wallet.entity.AssetEntity;

import java.math.BigDecimal;

public interface AssetService {

    BigDecimal convert(AssetEntity asset, Currency target);
}
