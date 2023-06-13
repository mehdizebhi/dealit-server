package ir.dealit.restful.api.v1.auth;

import ir.dealit.restful.dto.auth.AuthToken;
import ir.dealit.restful.dto.auth.SignInReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/auth")
public interface AuthenticationApi {

    @PostMapping("/token")
    ResponseEntity<AuthToken> authenticate(
            @RequestBody SignInReq request
    );


}
