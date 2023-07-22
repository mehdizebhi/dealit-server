package ir.dealit.restful.controller.v1;

import ir.dealit.restful.controller.v1.api.AuthenticationApi;
import ir.dealit.restful.dto.auth.AuthTokenReq;
import ir.dealit.restful.dto.auth.AuthToken;
import ir.dealit.restful.dto.auth.SignedInUser;
import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.util.hateoas.assembler.UserInfoRepresentationModelAssembler;
import ir.dealit.restful.service.dao.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApi {

    private final AuthenticationService service;
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
