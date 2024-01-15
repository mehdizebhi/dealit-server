package ir.dealit.restful.module.notification.service.impl;

import ir.dealit.restful.dto.enums.NotificationType;
import ir.dealit.restful.dto.notification.Notification;
import ir.dealit.restful.module.notification.repository.NotificationRepository;
import ir.dealit.restful.module.notification.service.NotificationService;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserAuthService userAuthService;

    @Override
    public Page<Notification> allNotifications(Pageable pageable, UserEntity user) {
        return null;
    }

    @Override
    public Notification notification(ObjectId id, UserEntity user) {
        return null;
    }

    @Override
    public Page<Notification> activeNotificationsByType(Pageable pageable, NotificationType type, UserEntity user) {
        var inboxId = userAuthService.getInboxId(user.getId());
        if (inboxId.isPresent()) {
            notificationRepository.findAllByTypeAndInbox(type, inboxId.get(), pageable);
        }
        return Page.empty();
    }

    @Override
    public void sendTo(UserEntity to, Notification notification) {

    }

    @Override
    public void sendTo(UserEntity from, UserEntity to, Notification notification) {

    }

    @Override
    public void update(Notification notification) {

    }

    @Override
    public void delete(Notification notification) {

    }
}
