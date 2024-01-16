package ir.dealit.restful.dto.transaction;

import ir.dealit.restful.dto.enums.Currency;
import lombok.Builder;

@Builder
public record TransactionSummary(
        double total,
        double percentage,
        Currency defaultCurrency
) {
}
