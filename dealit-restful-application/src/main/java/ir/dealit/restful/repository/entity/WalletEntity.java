package ir.dealit.restful.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
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
    private List<AssetEntity> assets;
    private @DocumentReference @Indexed(unique = true) AccountEntity owner;
    private List<InvoiceEntity> invoices;
    private List<PaymentEntity> payments;

    public WalletEntity(AccountEntity owner) {
        this.owner = owner;
        this.invoices = Collections.emptyList();
        this.payments = Collections.emptyList();
    }

    public void addAsset(AssetEntity assetEntity) {
        if (this.assets == null) {
            this.assets = new ArrayList<>();
        }
        this.assets.add(assetEntity);
    }
}
