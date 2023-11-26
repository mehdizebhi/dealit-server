package ir.dealit.restful.module.inbox.entity;

import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.module.contract.entity.ContractEntity;
import ir.dealit.restful.module.job.entity.InvitationEntity;
import ir.dealit.restful.module.notification.entity.NotificationEntity;
import ir.dealit.restful.module.payment.entity.PaymentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "inboxes")
public class InboxEntity {

    private @MongoId ObjectId id;
    private @DocumentReference @Indexed(unique = true) AccountEntity owner;
    private @DocumentReference List<NotificationEntity> notifications;
    private @DocumentReference List<Bookmarkable> bookmarks;
    // Todo: Add list of payments, transactions and reports here
    private List<InvitationEntity> invitations;

    public InboxEntity(AccountEntity owner) {
        this.owner = owner;
        this.invitations = Collections.emptyList();
        this.notifications = Collections.emptyList();
        this.bookmarks = Collections.emptyList();
    }
}
