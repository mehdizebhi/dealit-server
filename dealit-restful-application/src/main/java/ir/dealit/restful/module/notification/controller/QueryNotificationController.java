package ir.dealit.restful.module.notification.controller;

import ir.dealit.restful.api.query.QueryNotificationApi;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.notification.Notification;
import ir.dealit.restful.module.notification.service.NotificationService;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QueryNotificationController implements QueryNotificationApi {

    private final NotificationService notificationService;

    @Override
    public ResponseEntity<ResponseModel<List<Notification>>> getAllNotifications(Pageable pageable, Authentication authentication) {
        var pages = notificationService.allNotifications(pageable, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new ResponseModel.Builder<List<Notification>>()
                .data(pages.toList())
                .pageMetadata(pages)
                .success()
                .build());
    }
}
