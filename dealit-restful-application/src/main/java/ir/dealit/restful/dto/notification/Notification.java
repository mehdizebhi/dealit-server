package ir.dealit.restful.dto.notification;

import ir.dealit.restful.dto.enums.NotificationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Notification {

    private String id;
    private String title;
    private String description;
    private NotificationType type;
}
