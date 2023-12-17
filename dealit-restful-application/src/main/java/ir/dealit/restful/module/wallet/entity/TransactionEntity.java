package ir.dealit.restful.module.wallet.entity;

import ir.dealit.restful.dto.enums.TransactionReason;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "transactions")
public class TransactionEntity {
    private @MongoId ObjectId id;
    private @DocumentReference WalletEntity from;
    private @DocumentReference WalletEntity to;
    private AssetEntity amount;
    private TransactionReason reason;
    private @CreatedDate Date createdAt;
    private @LastModifiedDate Date updatedAt;
    private boolean seen;
}
