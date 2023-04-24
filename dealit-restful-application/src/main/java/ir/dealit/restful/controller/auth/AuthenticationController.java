package ir.dealit.restful.controller.auth;

import ir.dealit.restful.dto.auth.AuthenticationRequest;
import ir.dealit.restful.dto.auth.AuthenticationResponse;
import ir.dealit.restful.dto.auth.RegisterRequest;
import ir.dealit.restful.dto.auth.RegisterResponse;
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
    public ResponseEntity<RegisterResponse> registerUser(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
