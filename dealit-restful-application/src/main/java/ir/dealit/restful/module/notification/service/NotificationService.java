package ir.dealit.restful.module.notification.service;

import ir.dealit.restful.dto.enums.NotificationType;
import ir.dealit.restful.dto.notification.Notification;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;

@Secured("ROLE_USER")
public interface NotificationService {
    Page<Notification> allNotifications(Pageable pageable, UserEntity user);
    Notification notification(ObjectId id, UserEntity user);
    Page<Notification> activeNotificationsByType(Pageable pageable, NotificationType type, UserEntity user);
}
