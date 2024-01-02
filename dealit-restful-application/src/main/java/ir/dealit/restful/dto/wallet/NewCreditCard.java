package ir.dealit.restful.dto.wallet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record NewCreditCard(
        @Pattern(regexp = "^[0-9]{16}$", message = "Card number must be exactly 16 digits")
        String cardNumber,
        @NotBlank
        String ownerName,
        @Pattern(regexp = "^(0[1-9]|1[0-2])$", message = "Invalid expiration month")
        String expiredMonth,
        @Pattern(regexp = "^[0-9]{2}$", message = "Invalid expiration year")
        String expiredYear,
        String type,
        @NotBlank
        String bank,
        @Pattern(regexp = "^[0-9]{24}$", message = "IBAN number must be exactly 24 digits")
        String iban
) {
}
