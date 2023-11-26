package ir.dealit.restful.module.notification.entity;

import ir.dealit.restful.dto.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

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
}
