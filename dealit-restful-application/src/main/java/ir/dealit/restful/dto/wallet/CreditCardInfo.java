package ir.dealit.restful.dto.wallet;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CreditCardInfo(
        String cardNumber,
        String ownerName,
        String expiredMonth,
        String expiredYear,
        String type,
        String bank,
        String iban,
        boolean confirmed,
        boolean payable
) {
}
