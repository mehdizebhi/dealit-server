package ir.dealit.restful.module.wallet.entity;

import ir.dealit.restful.dto.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetEntity {

    private BigDecimal amount;
    private Currency currency;

    public Money toMoney() {
        return Money.of(CurrencyUnit.of(currency.getCode()), amount);
    }
}
