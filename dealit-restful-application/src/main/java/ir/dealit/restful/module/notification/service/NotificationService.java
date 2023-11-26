package ir.dealit.restful.module.notification.service;

import ir.dealit.restful.dto.notification.Notification;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.Optional;

public interface NotificationService {
    Optional<PagedModel<Notification>> allNotifications(Pageable pageable, Authentication authentication);
    Optional<EntityModel<Notification>> notification(ObjectId id, Authentication authentication);
}
