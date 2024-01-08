package ir.dealit.restful.api;

import ir.dealit.restful.dto.auth.*;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.dto.user.ResetPassword;
import jakarta.validation.constraints.Email;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/auth")
public interface AuthenticationApi {

    @PostMapping("/token")
    ResponseEntity<ResponseModel<AuthToken>> signIn(
            @RequestBody AuthTokenRequest request
    );

    @PostMapping("/signup")
    ResponseEntity<ResponseModel<SignedInUser>> signUp(
            @RequestBody NewUser newUser
    );

    @PostMapping("/refresh-token")
    ResponseEntity<ResponseModel<AuthToken>> refreshAccessToken(
            @RequestBody TokenRefreshRequest request,
            Authentication authentication
    );

    @PostMapping("/logout")
    ResponseEntity<ResponseModel<Void>> logout(
            Authentication authentication
    );

    @PostMapping("/forget-password")
    ResponseEntity<ResponseModel<Void>> forgetPassword(
            @RequestParam("email") @Email String email
    );

    @GetMapping("/check-reset-password-token")
    ResponseEntity<ResponseModel<Void>> checkResetPasswordToken(
            @RequestParam(value = "token") String token
    );

    @PostMapping("/reset-password")
    ResponseEntity<ResponseModel<Void>> resetPassword(
            @RequestParam(value = "token") String token,
            @RequestBody ResetPassword resetPassword
    );
}
