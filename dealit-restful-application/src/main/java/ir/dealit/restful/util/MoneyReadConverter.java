package ir.dealit.restful.util;

import ir.dealit.restful.module.wallet.entity.AssetEntity;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class MoneyReadConverter implements Converter<AssetEntity, Money> {

    @Override
    public Money convert(AssetEntity source) {
        return Money.of(CurrencyUnit.of(source.getCurrency().getCode()), source.getAmount());
    }
}
