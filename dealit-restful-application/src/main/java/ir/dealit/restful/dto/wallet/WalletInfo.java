package ir.dealit.restful.dto.wallet;

import lombok.Builder;

@Builder
public record WalletInfo(
    double balance
) {
}
