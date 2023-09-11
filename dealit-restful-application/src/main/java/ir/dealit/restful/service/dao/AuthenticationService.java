package ir.dealit.restful.service.dao;

import ir.dealit.restful.config.security.jwt.util.JwtUtilsImpl;
import ir.dealit.restful.dto.auth.*;
import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.repository.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtUtilsImpl jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserDaoService service;

    @Value("${app.security.jwt.period}")
    private long EXPIRATION_PERIOD;

    public Optional<SignedInUser> register(NewUser newUser) {
        Optional<UserEntity> userEntity = service.registerUser(newUser);
        return userEntity.map(u -> {
            return Optional.of(SignedInUser.builder()
                    .username(u.getUsername())
                    .userId(u.getId().toString())
                    .accessToken(AuthToken.builder()
                            .token(jwtUtils.generateToken(u))
                            .type("Bearer")
                            .exp((new Date().getTime() + EXPIRATION_PERIOD) / 1_000)
                            .build())
                    .build()
            );
        }).orElse(Optional.empty());
    }

    public Optional<SignedInUser> authenticate(AuthTokenReq req) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getUsername(),
                            req.getPassword()
                    )
            );
        } catch (Exception exp) {
            throw new BadCredentialsException("Something happen wrongly in authenticate");
        }
        UserEntity user = service.findUserByUsername(req.getUsername());
        return Objects.nonNull(user) ? Optional.of(
                SignedInUser.builder()
                        .userId(user.getId().toString())
                        .username(user.getUsername())
                        .accessToken(AuthToken.builder()
                                .token(jwtUtils.generateToken(user))
                                .type("Bearer")
                                .exp((new Date().getTime() + EXPIRATION_PERIOD) / 1_000)
                                .build())
                        .build()
        ) : Optional.empty();

    }


/*    public UserSignUpRes register(UserSignUpReq regRequest) {
        UserEntity userEntity = UserEntity.builder()
                .username(regRequest.getUsername())
                .password(passwordEncoder.encode(regRequest.getPassword()))
                .displayName(regRequest.getDisplayName())
                .email(regRequest.getEmail())
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .enabled(true)
                .build();
        userRepository.save(userEntity);
        return userEntity != null ? UserSignUpRes.builder().successfulRegister(true).build()
                : UserSignUpRes.builder().successfulRegister(false).build();
    }*/

/*    public AuthToken authenticate(AuthTokenReq authReauest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authReauest.getUsername(),
                            authReauest.getPassword()
                    )
            );
        } catch (Exception exp) {
            throw new BadCredentialsException("Something happen wrongly in authenticate");
        }
        UserEntity userEntity = userRepository.findByUsername(authReauest.getUsername());
        return userEntity != null ? AuthToken.builder()
                .token(jwtUtils.generateToken(userEntity))
                .build()
                : AuthToken.builder().token(null).build();
    }*/
}

