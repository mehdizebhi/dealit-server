package ir.dealit.restful.module.user.controller;

import ir.dealit.restful.api.AuthenticationApi;
import ir.dealit.restful.dto.auth.*;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.dto.user.ResetPassword;
import ir.dealit.restful.module.user.service.TokenService;
import ir.dealit.restful.module.user.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController implements AuthenticationApi {

    private final AuthenticationService service;
    private final TokenService tokenService;

    @Override
    public ResponseEntity<ResponseModel<AuthToken>> signIn(@Valid AuthTokenRequest request) {
            return ResponseEntity.ok(new ResponseModel.Builder<AuthToken>()
                    .data(service.authenticate(request).getToken())
                    .success()
                    .build());
    }

    @Override
    public ResponseEntity<ResponseModel<SignedInUser>> signUp(NewUser newUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseModel.Builder<SignedInUser>()
                .data(service.register(newUser))
                .success()
                .build()
        );
    }

    @Override
    public ResponseEntity<ResponseModel<AuthToken>> refreshAccessToken(TokenRefreshRequest request, Authentication authentication) {
        return ResponseEntity.ok(new ResponseModel.Builder<AuthToken>()
                .data(tokenService.renewAccessToken(request.refreshToken()))
                .success()
                .build()
        );
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
    public ResponseEntity<ResponseModel<Void>> checkResetPasswordToken(String token) {
        if (service.checkResetPasswordTokenIsExist(token)) {
            return ResponseEntity.ok(new ResponseModel.Builder<Void>().success().build());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel.Builder<Void>().error("The reset password token in not found").build());
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> resetPassword(String token, ResetPassword resetPassword) {
        service.verifyResetTokenAndUpdatePassword(token, resetPassword);
        return ResponseEntity.ok(new ResponseModel.Builder<Void>().success().build());
    }
}
