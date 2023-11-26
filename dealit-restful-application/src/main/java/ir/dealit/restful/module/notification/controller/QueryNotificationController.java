package ir.dealit.restful.module.notification.controller;

import ir.dealit.restful.api.query.QueryNotificationApi;
import ir.dealit.restful.dto.notification.Notification;
import ir.dealit.restful.module.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.*;


@RestController
@RequiredArgsConstructor
public class QueryNotificationController implements QueryNotificationApi {

    private final NotificationService notificationService;

    @Override
    public ResponseEntity<PagedModel<Notification>> getAllNotifications(Pageable pageable, Authentication authentication) {
        return notificationService
                .allNotifications(pageable, authentication)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @Override
    public ResponseEntity<EntityModel<Notification>> getNotification(ObjectId id, Authentication authentication) {
        return notificationService
                .notification(id, authentication)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }
}
