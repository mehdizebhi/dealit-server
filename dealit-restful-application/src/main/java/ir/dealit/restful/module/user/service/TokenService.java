package ir.dealit.restful.module.user.service;

import ir.dealit.restful.dto.auth.AuthToken;
import ir.dealit.restful.module.user.entity.UserEntity;

import java.util.Optional;

public interface TokenService {

    AuthToken createToken(UserEntity user);

    void remove(String token);

    void expireToken(String token);

    AuthToken renewAccessToken(String refreshToken);

    String getRefreshToken(String token);
}
