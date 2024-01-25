package ir.dealit.restful.dto.notification;

import ir.dealit.restful.dto.enums.NotificationType;
import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

@Builder
public record Notification(
        String id,
        String title,
        String description,
        NotificationType type,
        boolean read,
        DateTime createdAt
) {
}
