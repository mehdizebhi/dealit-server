package ir.dealit.restful.api;

import ir.dealit.restful.dto.auth.*;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.user.NewUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/auth")
public interface AuthenticationApi {

    @PostMapping("/token")
    ResponseEntity<AuthToken> signIn(
            @RequestBody AuthTokenRequest request
    );

    @PostMapping("/signup")
    ResponseEntity<SignedInUser> singUp (
            @RequestBody NewUser newUser
    );

    @PostMapping("/refresh-token")
    ResponseEntity<AuthToken> refreshAccessToken(
            @RequestBody TokenRefreshRequest request,
            Authentication authentication
    );

    @PostMapping("/logout")
    ResponseEntity<ResponseModel<Void>> logout(
            Authentication authentication
    );

    @PostMapping("/forget-password")
    ResponseEntity<ResponseModel<Void>> forgetPassword(
            @RequestParam("email") String email
    );

    @PostMapping("/reset-password")
    ResponseEntity<String> resetPassword(
            @RequestParam(value = "email", required = true) String email
    );
}
