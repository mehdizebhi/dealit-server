package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.notification.Notification;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/v1/notifications")
public interface QueryNotificationApi {

    @GetMapping("")
    ResponseEntity<ResponseModel<List<Notification>>> getAllNotifications(
            @PageableDefault(sort = {"createdAt"}, direction = Sort.Direction.DESC) Pageable pageable,
            Authentication authentication
    );
}
