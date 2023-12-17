package ir.dealit.restful.module.user.service;

import ir.dealit.restful.dto.auth.AuthToken;
import ir.dealit.restful.module.user.entity.UserEntity;

public interface TokenService {

    AuthToken createToken(UserEntity user);
    void remove(String token);
}
