package ir.dealit.restful.api.auth;

import ir.dealit.restful.dto.auth.AuthToken;
import ir.dealit.restful.dto.auth.AuthTokenReq;
import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.dto.user.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/auth")
public interface AuthenticationApiV1 {

    @PostMapping("/token")
    ResponseEntity<AuthToken> authenticate(
            @RequestBody AuthTokenReq request
    );

    @PostMapping("/signup")
    ResponseEntity<UserInfo> singUp (
            @RequestBody NewUser newUser
    );


}
