package ir.dealit.restful.api;

import ir.dealit.restful.dto.ResponseWrapper;
import ir.dealit.restful.dto.auth.AuthToken;
import ir.dealit.restful.dto.auth.AuthTokenReq;
import ir.dealit.restful.dto.auth.OTPCode;
import ir.dealit.restful.dto.auth.SignedInUser;
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
            @RequestBody AuthTokenReq request
    );

    @PostMapping("/signup")
    ResponseEntity<SignedInUser> singUp (
            @RequestBody NewUser newUser
    );

    @PostMapping("/logout")
    ResponseEntity<Void> logout(
            Authentication authentication
    );

    @PostMapping("/reset-password")
    ResponseEntity<String> resetPassword(
            @RequestParam(value = "email", required = true) String email
    );

    @PostMapping("/otp")
    ResponseEntity<ResponseWrapper<String>> createOTP(
            Authentication authentication
    );

    @PostMapping("/verify-otp")
    ResponseEntity<ResponseWrapper<String>> verifyOTP(
            @RequestBody OTPCode code,
            Authentication authentication
    );
}
