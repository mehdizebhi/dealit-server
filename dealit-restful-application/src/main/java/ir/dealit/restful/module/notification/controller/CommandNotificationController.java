package ir.dealit.restful.module.notification.controller;

import ir.dealit.restful.api.command.CommandNotificationApi;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.notification.Notification;
import ir.dealit.restful.module.notification.service.NotificationService;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CommandNotificationController implements CommandNotificationApi {

    private final NotificationService notificationService;

    @Override
    public ResponseEntity<ResponseModel<Void>> sendNotification(List<String> usernames, Notification notification, Authentication authentication) {
        notificationService.sendToAll(usernames, notification);
        return ResponseEntity.ok(new ResponseModel.Builder<Void>()
                .success()
                .build()
        );
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> readNotifications(List<ObjectId> ids, Authentication authentication) {
        notificationService.read(ids, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new ResponseModel.Builder<Void>()
                .success()
                .build()
        );
    }
}
