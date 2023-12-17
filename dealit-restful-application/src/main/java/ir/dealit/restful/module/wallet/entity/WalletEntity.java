package ir.dealit.restful.module.wallet.entity;

import ir.dealit.restful.module.payment.entity.PaymentEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.joda.money.Money;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "wallets")
public class WalletEntity {

    private @MongoId ObjectId id;
    private List<Money> monies;
    private @DocumentReference @Indexed(unique = true) UserEntity owner;
    private @DocumentReference List<InvoiceEntity> invoices;
    private @DocumentReference List<PaymentEntity> payments;
    private @DocumentReference List<TransactionEntity> transactions;

    public WalletEntity(UserEntity owner) {
        this.owner = owner;
        this.invoices = Collections.emptyList();
        this.payments = Collections.emptyList();
        this.transactions = Collections.emptyList();
    }

    public void addMoney(Money money) {
        if (this.monies == null) {
            this.monies = new ArrayList<>();
        }
        this.monies.add(money);
    }
}
