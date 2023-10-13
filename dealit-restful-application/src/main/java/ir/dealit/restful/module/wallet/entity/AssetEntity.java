package ir.dealit.restful.module.wallet.entity;

import ir.dealit.restful.module.wallet.entity.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetEntity {

    private BigDecimal amount;
    private Currency currency;
}
