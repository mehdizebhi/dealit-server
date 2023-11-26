package ir.dealit.restful.module.notification.service.impl;

import ir.dealit.restful.dto.notification.Notification;
import ir.dealit.restful.module.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    @Override
    public Optional<PagedModel<Notification>> allNotifications(Pageable pageable, Authentication authentication) {
        return Optional.empty();
    }

    @Override
    public Optional<EntityModel<Notification>> notification(ObjectId id, Authentication authentication) {
        return Optional.empty();
    }
}
