package ir.dealit.restful.module.notification.entity;

import ir.dealit.restful.dto.enums.NotificationType;
import ir.dealit.restful.module.inbox.entity.InboxEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "notifications")
public class NotificationEntity {
    private @MongoId ObjectId id;
    private String title;
    private String description;
    private NotificationType type;
    private boolean read;
    private @DocumentReference InboxEntity inbox;
    private @CreatedDate Date createdAt;
}
