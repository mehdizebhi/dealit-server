package ir.dealit.restful.controller.v1.api;

import ir.dealit.restful.dto.auth.AuthToken;
import ir.dealit.restful.dto.auth.AuthTokenReq;
import ir.dealit.restful.dto.auth.SignedInUser;
import ir.dealit.restful.dto.user.NewUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/auth")
public interface AuthenticationApi {

    @PostMapping("/token")
    ResponseEntity<AuthToken> signIn(
            @RequestBody AuthTokenReq request
    );

    @PostMapping("/signup")
    ResponseEntity<SignedInUser> singUp (
            @RequestBody NewUser newUser
    );

    @PostMapping("/reset-password")
    ResponseEntity<String> resetPassword(
            @RequestParam(value = "email", required = true) String email
    );
}
