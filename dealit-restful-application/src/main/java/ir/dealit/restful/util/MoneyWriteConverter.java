package ir.dealit.restful.util;

import ir.dealit.restful.dto.enums.Currency;
import ir.dealit.restful.module.wallet.entity.AssetEntity;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class MoneyWriteConverter implements Converter<Money, AssetEntity> {

    @Override
    public AssetEntity convert(Money source) {
        return new AssetEntity(source.getAmount(), Currency.valueOf(source.getCurrencyUnit().getCode()));
    }
}
