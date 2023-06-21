package ir.dealit.restful.controller.v1.auth;

import ir.dealit.restful.api.auth.AuthenticationApiV1;
import ir.dealit.restful.dto.auth.AuthTokenReq;
import ir.dealit.restful.dto.auth.AuthToken;
import ir.dealit.restful.dto.auth.SignedInUser;
import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.dto.user.UserInfo;
import ir.dealit.restful.hateoas.assembler.UserInfoRepresentationModelAssembler;
import ir.dealit.restful.service.auth.AuthenticationServiceImpl;
import ir.dealit.restful.service.user.UserDaoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApiV1 {

    private final AuthenticationServiceImpl service;
    private final UserInfoRepresentationModelAssembler assembler;

//    @PostMapping("/register")
//    public ResponseEntity<UserSignUpRes> registerUser(
//            @RequestBody UserSignUpReq request
//    ) {
//        return ResponseEntity.ok(authService.register(request));
//    }

    @Override
    public ResponseEntity<AuthToken> signIn(
            @Valid AuthTokenReq request
    ) {
        return service.authenticate(request)
                .map(s -> AuthToken.builder().token(s.getAccessToken()).build())
                .map(ResponseEntity::ok)
                .orElse(status(HttpStatus.UNAUTHORIZED).build());
    }

    @Override
    public ResponseEntity<SignedInUser> singUp(NewUser newUser) {
        return service.register(newUser)
                .map(model -> status(HttpStatus.CREATED).body(model))
                .orElse(badRequest().build());
    }
}
