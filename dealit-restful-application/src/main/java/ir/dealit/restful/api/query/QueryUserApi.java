package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.user.UserInfo;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/users")
public interface QueryUserApi {

    @GetMapping("/info")
    @Secured("ROLE_USER")
    ResponseEntity<EntityModel<UserInfo>> getUserInfo(
            Authentication authentication
    );
}
