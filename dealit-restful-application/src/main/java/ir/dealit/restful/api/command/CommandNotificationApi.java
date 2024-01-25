package ir.dealit.restful.api.command;

import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.notification.Notification;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/notifications")
public interface CommandNotificationApi {

    @PostMapping("")
    @Secured("ROLE_ADMIN")
    ResponseEntity<ResponseModel<Void>> sendNotification(
            @RequestParam("to") List<String> usernames,
            @RequestBody Notification notification,
            Authentication authentication
    );

    @PatchMapping("/read")
    ResponseEntity<ResponseModel<Void>> readNotifications(
            @RequestParam("ids") List<ObjectId> ids,
            Authentication authentication
    );
}
