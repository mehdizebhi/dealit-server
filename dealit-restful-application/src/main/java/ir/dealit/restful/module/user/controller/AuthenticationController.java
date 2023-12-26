package ir.dealit.restful.module.user.controller;

import ir.dealit.restful.api.AuthenticationApi;
import ir.dealit.restful.dto.ResponseWrapper;
import ir.dealit.restful.dto.auth.*;
import ir.dealit.restful.dto.enums.OTPSenderMechanism;
import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.service.TokenService;
import ir.dealit.restful.util.exception.UserFoundExeption;
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
    public ResponseEntity<SignedInUser> singUp(NewUser newUser) {
        try {
            return service.register(newUser)
                    .map(model -> status(HttpStatus.CREATED).body(model))
                    .orElse(badRequest().build());
        }
        catch (UserFoundExeption exp) {
            return status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @Override
    public ResponseEntity<AuthToken> refreshAccessToken(TokenRefreshRequest request, Authentication authentication) {
        return ResponseEntity.ok(tokenService.renewAccessToken(request.refreshToken()));
    }

    @Override
    public ResponseEntity<Void> logout(Authentication authentication) {
        service.logout((String) authentication.getCredentials());
        return ResponseEntity.status(200).build();
    }

    @Override
    public ResponseEntity<String> resetPassword(String email) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseWrapper<String>> createOTP(Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        if (!user.isPhoneConfirmed()) {
            try {
                service.sendOTP((UserEntity) authentication.getPrincipal(), OTPSenderMechanism.SMS);
                return ResponseEntity.ok(new ResponseWrapper<>(null, "success"));
            } catch (Exception exp) {
                return ResponseEntity.badRequest().body(new ResponseWrapper<>(null, "fail"));
            }
        }
        return ResponseEntity.ok(new ResponseWrapper<>(null, "success"));
    }

    @Override
    public ResponseEntity<ResponseWrapper<String>> verifyOTP(OTPCode code, Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        if (!user.isPhoneConfirmed()) {
            boolean verified = service.verifyOTPCode(code.code(), user);
            if (verified) {
                return ResponseEntity.ok(new ResponseWrapper<>(null, "success"));
            } else {
                return ResponseEntity.badRequest().body(new ResponseWrapper<>(null, "fail"));
            }
        }
        return ResponseEntity.ok(new ResponseWrapper<>(null, "success"));
    }
}
