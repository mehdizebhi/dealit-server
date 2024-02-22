package ir.dealit.restful.module.notification.service;

import ir.dealit.restful.dto.enums.NotificationType;
import ir.dealit.restful.dto.notification.Notification;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

@Secured("ROLE_USER")
public interface NotificationService {

    Page<Notification> allNotifications(Pageable pageable, UserEntity user);

    Notification notification(ObjectId id, UserEntity user);

    Page<Notification> unreadNotificationsByType(Pageable pageable, NotificationType type, UserEntity user);

    void sendTo(UserEntity to, Notification notification);

    void sendTo(UserEntity from, UserEntity to, Notification notification);

    void sendToAsync(ObjectId userId, Notification notification);

    List<ObjectId> sendToAll(List<String> usernames, Notification notification);

    void read(List<ObjectId> notificationIds, UserEntity owner);

    void update(Notification notification);

    void delete(Notification notification);
}
