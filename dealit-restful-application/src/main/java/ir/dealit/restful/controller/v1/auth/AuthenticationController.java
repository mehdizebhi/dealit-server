package ir.dealit.restful.controller.v1.auth;

import ir.dealit.restful.api.v1.auth.AuthenticationApi;
import ir.dealit.restful.dto.auth.SignInReq;
import ir.dealit.restful.dto.auth.AuthToken;
import ir.dealit.restful.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApi {

    private final AuthenticationService authService;

//    @PostMapping("/register")
//    public ResponseEntity<UserSignUpRes> registerUser(
//            @RequestBody UserSignUpReq request
//    ) {
//        return ResponseEntity.ok(authService.register(request));
//    }

    @Override
    public ResponseEntity<AuthToken> authenticate(
            @Valid SignInReq request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
