package ir.dealit.restful.module.user.controller;

import ir.dealit.restful.api.AuthenticationApi;
import ir.dealit.restful.dto.auth.*;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.dto.user.ResetPassword;
import ir.dealit.restful.module.user.service.TokenService;
import ir.dealit.restful.util.exception.UserFoundException;
import ir.dealit.restful.module.user.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController implements AuthenticationApi {

    private final AuthenticationService service;
    private final TokenService tokenService;

    @Override
    public ResponseEntity<AuthToken> signIn(
            @Valid AuthTokenRequest request
    ) {
            return service.authenticate(request)
                    .map(s -> s.getToken())
                    .map(ResponseEntity::ok)
                    .orElse(status(HttpStatus.UNAUTHORIZED).build());
    }

    @Override
    public ResponseEntity<SignedInUser> signUp(NewUser newUser) {
        try {
            return service.register(newUser)
                    .map(model -> status(HttpStatus.CREATED).body(model))
                    .orElse(badRequest().build());
        }
        catch (UserFoundException exp) {
            return status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @Override
    public ResponseEntity<AuthToken> refreshAccessToken(TokenRefreshRequest request, Authentication authentication) {
        return ResponseEntity.ok(tokenService.renewAccessToken(request.refreshToken()));
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> logout(Authentication authentication) {
        service.logout((String) authentication.getCredentials());
        return ResponseEntity.ok(new ResponseModel.Builder<Void>().success().build());
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> forgetPassword(String email) {
        service.sendForgetPasswordToken(email);
        return ResponseEntity.ok(new ResponseModel.Builder<Void>().success().build());
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> resetPassword(String token, ResetPassword resetPassword) {
        service.verifyResetTokenAndUpdatePassword(token, resetPassword);
        return ResponseEntity.ok(new ResponseModel.Builder<Void>().success().build());
    }
}
