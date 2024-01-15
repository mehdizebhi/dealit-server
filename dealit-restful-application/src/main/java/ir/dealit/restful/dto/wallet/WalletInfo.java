package ir.dealit.restful.dto.wallet;

import ir.dealit.restful.dto.enums.Currency;
import lombok.Builder;

@Builder
public record WalletInfo(
    double balance,
    double blockBalance,
    double totalBalance,
    double totalIncome,
    double totalOutcome,
    Currency defaultCurrency
) {
}
