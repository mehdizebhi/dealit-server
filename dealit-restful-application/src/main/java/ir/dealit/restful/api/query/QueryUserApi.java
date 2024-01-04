package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.user.UserActivity;
import ir.dealit.restful.dto.user.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/v1/users")
public interface QueryUserApi {

    @GetMapping("/info")
    @Secured("ROLE_USER")
    ResponseEntity<ResponseModel<UserInfo>> getUserInfo(
            Authentication authentication
    );

    @GetMapping("/activities")
    @Secured("ROLE_USER")
    ResponseEntity<ResponseModel<List<UserActivity>>> getUserActivities(
            @PageableDefault Pageable pageable,
            Authentication authentication
    );
}
