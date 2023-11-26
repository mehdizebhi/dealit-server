package ir.dealit.restful.dto.wallet;

import ir.dealit.restful.dto.enums.Currency;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Asset {
    private BigDecimal amount;
    private Currency currency;
}
