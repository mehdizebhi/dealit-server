package ir.dealit.restful.api.command;

import ir.dealit.restful.dto.user.UpdateUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/users")
public interface CommandUserApi {

    @PutMapping("/")
    ResponseEntity<Void> updateUser(
            @RequestBody UpdateUser updateUserReq,
            Authentication authentication
    );

    @PatchMapping("/disable")
    ResponseEntity<Void> disableUser(
            Authentication authentication
    );

    @PatchMapping("/enable")
    ResponseEntity<Void> enableUser(
            Authentication authentication
    );
}
