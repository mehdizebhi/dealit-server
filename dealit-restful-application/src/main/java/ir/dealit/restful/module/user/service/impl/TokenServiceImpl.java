package ir.dealit.restful.module.user.service.impl;

import ir.dealit.restful.config.security.jwt.util.JwtUtilsImpl;
import ir.dealit.restful.dto.auth.AuthToken;
import ir.dealit.restful.module.user.entity.TokenEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.repository.TokenRepository;
import ir.dealit.restful.module.user.service.TokenService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final JwtUtilsImpl jwtUtils;

    @Value("${app.security.jwt.period}")
    private long EXPIRATION_PERIOD;

    @Override
    public AuthToken createToken(@Valid @NotNull UserEntity user) {
        var expiredAt = DateTime.now().plusSeconds((int) (EXPIRATION_PERIOD / 1_000));
        var token = AuthToken.builder()
                .accessToken(jwtUtils.generateToken(user))
                .refreshToken(UUID.randomUUID().toString())
                .type("Bearer")
                .exp(expiredAt.getMillis() / 1_000)
                .build();

        tokenRepository.save(TokenEntity.builder()
                        .token(token.getAccessToken())
                        .expiredAt(expiredAt.toDate())
                        .refreshToken(token.getRefreshToken())
                        .user(user)
                        .expired(false)
                .build());

        return token;
    }

    @Override
    public void remove(String token) {
        tokenRepository.deleteByToken(token);
    }

    @Override
    public void expireToken(String token) {
        var entityOptional = tokenRepository.findByToken(token);
        if (entityOptional.isPresent()){
            var entity = entityOptional.get();
            entity.setExpired(true);
            entity.setExpiredAt(new Date());
            tokenRepository.save(entity);
        }
    }

    @Override
    public AuthToken renewAccessToken(String refreshToken){
        var tokenOptional = tokenRepository.findByRefreshToken(refreshToken);
        if (tokenOptional.isPresent()){
            var tokenEntity = tokenOptional.get();
            var expiredAt = DateTime.now().plusSeconds((int) (EXPIRATION_PERIOD / 1_000));
            tokenEntity.setToken(jwtUtils.generateToken(tokenEntity.getUser()));
            tokenEntity.setExpiredAt(expiredAt.toDate());
            tokenRepository.save(tokenEntity);

            return AuthToken.builder()
                    .accessToken(tokenEntity.getToken())
                    .refreshToken(tokenEntity.getRefreshToken())
                    .type("Bearer")
                    .exp(expiredAt.getMillis() / 1_000)
                    .build();
        }
        throw new RuntimeException("Invalid Refresh Token!");
    }

    @Override
    public String getRefreshToken(String token) {
        var tokenOptional = tokenRepository.findByToken(token);
        if (tokenOptional.isPresent()){
            return tokenOptional.get().getRefreshToken();
        }
        throw new RuntimeException("Invalid Access Token!");
    }
}
