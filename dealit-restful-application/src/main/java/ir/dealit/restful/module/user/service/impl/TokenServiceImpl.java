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

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

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
                .refreshToken(null)
                .type("Bearer")
                .exp(expiredAt.getMillis() / 1_000)
                .build();

        tokenRepository.save(TokenEntity.builder()
                        .token(token.getAccessToken())
                        .expiredAt(expiredAt.toDate())
                        .user(user)
                        .blocked(false)
                .build());

        return token;
    }

    @Override
    public void remove(String token) {
        tokenRepository.deleteByToken(token);
    }
}
