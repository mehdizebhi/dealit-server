package ir.dealit.restful.controller.v1.auth;

import ir.dealit.restful.api.auth.AuthenticationApiV1;
import ir.dealit.restful.dto.auth.AuthTokenReq;
import ir.dealit.restful.dto.auth.AuthToken;
import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.dto.user.UserInfo;
import ir.dealit.restful.hateoas.assembler.UserInfoRepresentationModelAssembler;
import ir.dealit.restful.service.auth.AuthenticationService;
import ir.dealit.restful.service.user.UserDaoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.badRequest;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApiV1 {

    private final AuthenticationService authService;
    private final UserDaoServiceImpl service;
    private final UserInfoRepresentationModelAssembler assembler;

//    @PostMapping("/register")
//    public ResponseEntity<UserSignUpRes> registerUser(
//            @RequestBody UserSignUpReq request
//    ) {
//        return ResponseEntity.ok(authService.register(request));
//    }

    @Override
    public ResponseEntity<AuthToken> authenticate(
            @Valid AuthTokenReq request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @Override
    public ResponseEntity<UserInfo> singUp(NewUser newUser) {
        return service.registerUser(newUser)
                .map(assembler::toModel)
                .map(model -> new ResponseEntity(model, HttpStatus.CREATED))
                .orElse(badRequest().build());
    }
}
