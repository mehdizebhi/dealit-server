package ir.dealit.restful.module.notification.service.impl;

import ir.dealit.restful.dto.enums.NotificationType;
import ir.dealit.restful.dto.notification.Notification;
import ir.dealit.restful.module.inbox.entity.InboxEntity;
import ir.dealit.restful.module.notification.entity.NotificationEntity;
import ir.dealit.restful.module.notification.repository.NotificationRepository;
import ir.dealit.restful.module.notification.service.NotificationService;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.repository.UserRepository;
import ir.dealit.restful.module.user.service.UserAuthService;
import ir.dealit.restful.util.exception.NotFoundResourceException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final UserAuthService userAuthService;

    @Override
    public Page<Notification> allNotifications(Pageable pageable, UserEntity user) {
        return notificationRepository.findByInbox(user.getInbox(), pageable).map(this::toModel);
    }

    @Override
    public Notification notification(ObjectId id, UserEntity user) {
        return null;
    }

    @Override
    public Page<Notification> unreadNotificationsByType(Pageable pageable, NotificationType type, UserEntity user) {
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
    @Async
    public void sendToAsync(ObjectId userId, Notification notification) {
        var inbox = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundResourceException("user id is not found"))
                .getInbox();
        notificationRepository.save(toEntity(notification, inbox));
    }

    @Override
    @Transactional
    public List<ObjectId> sendToAll(List<String> usernames, Notification notification) {
        List<InboxEntity> inboxes = userRepository.findAllByUsername(usernames).stream().map(UserEntity::getInbox).collect(Collectors.toList());
        List<ObjectId> notificationIds = new ArrayList<>();
        for (var inbox : inboxes) {
            var entity = notificationRepository.save(toEntity(notification, inbox));
            notificationIds.add(entity.getId());
        }
        return notificationIds;
    }

    @Override
    @Transactional
    public void read(List<ObjectId> notificationIds, UserEntity owner) {
        var entities = notificationRepository.findAllByIdsAndInboxId(notificationIds, owner.getInbox().getId());
        for (var entity : entities) {
            entity.setRead(true);
        }
        notificationRepository.saveAll(entities);
    }

    @Override
    public void update(Notification notification) {

    }

    @Override
    public void delete(Notification notification) {

    }

    private Notification toModel(NotificationEntity entity) {
        return Notification.builder()
                .id(entity.getId().toString())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .type(entity.getType())
                .read(entity.isRead())
                .createdAt(new DateTime(entity.getCreatedAt()))
                .build();
    }

    private NotificationEntity toEntity(Notification model, InboxEntity inbox) {
        return NotificationEntity.builder()
                .title(model.title())
                .description(model.description())
                .type(model.type())
                .read(false)
                .inbox(inbox)
                .build();
    }
}
