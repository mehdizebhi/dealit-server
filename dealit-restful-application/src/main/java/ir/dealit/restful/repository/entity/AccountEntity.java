package ir.dealit.restful.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "accounts")
public abstract class AccountEntity {

    protected @MongoId ObjectId id;
    protected @DocumentReference(lazy = true) UserEntity user;
    protected @DocumentReference WalletEntity wallet;
    protected @DocumentReference InboxEntity inbox;
    protected @DocumentReference ChatEntity chat;

    public AccountEntity(UserEntity user, WalletEntity wallet, InboxEntity inbox, ChatEntity chat) {
        this.user = user;
        this.wallet = wallet;
        this.inbox = inbox;
        this.chat = chat;
    }
}
