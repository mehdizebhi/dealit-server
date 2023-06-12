package ir.dealit.restful.controller.v1.auth;

import ir.dealit.restful.dto.auth.SignInReq;
import ir.dealit.restful.dto.auth.AuthTokenRes;
import ir.dealit.restful.dto.auth.UserSignUpReq;
import ir.dealit.restful.dto.auth.UserSignUpRes;
import ir.dealit.restful.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<UserSignUpRes> registerUser(
            @RequestBody UserSignUpReq request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthTokenRes> authenticateUser(
            @RequestBody SignInReq request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
