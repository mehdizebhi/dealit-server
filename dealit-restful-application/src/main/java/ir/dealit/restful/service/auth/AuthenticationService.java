package ir.dealit.restful.service.auth;

import ir.dealit.restful.dto.auth.AuthTokenReq;
import ir.dealit.restful.dto.auth.SignedInUser;
import ir.dealit.restful.dto.auth.UserSignUpReq;
import ir.dealit.restful.dto.user.NewUser;

import java.util.Optional;

public interface AuthenticationService {

    Optional<SignedInUser> register(NewUser newUser);
    Optional<SignedInUser> authenticate(AuthTokenReq token);

}
