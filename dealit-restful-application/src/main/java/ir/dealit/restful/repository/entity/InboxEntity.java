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
    private List<InvitationEntity> invitations;
    private List<NotificationEntity> notifications;

    public InboxEntity(AccountEntity owner) {
        this.owner = owner;
        this.invitations = Collections.emptyList();
        this.notifications = Collections.emptyList();
    }
}
