package ir.dealit.restful.module.wallet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardEntity {

    private String cardNumber;
    private String ownerName;
    private String expiredMonth;
    private String expiredYear;
    private String type;
    private String bank;
    private String iban;
    private boolean confirmed;
    private boolean payable;
}
