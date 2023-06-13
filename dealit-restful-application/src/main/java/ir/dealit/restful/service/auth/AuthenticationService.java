package ir.dealit.restful.service.auth;

import ir.dealit.restful.config.security.jwt.util.JwtUtilsImpl;
import ir.dealit.restful.dto.auth.AuthTokenReq;
import ir.dealit.restful.dto.auth.AuthToken;
import ir.dealit.restful.dto.auth.UserSignUpReq;
import ir.dealit.restful.dto.auth.UserSignUpRes;
import ir.dealit.restful.entity.user.UserEntity;
import ir.dealit.restful.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilsImpl jwtUtils;
    private final AuthenticationManager authenticationManager;

    public UserSignUpRes register(UserSignUpReq regRequest) {
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
    }

    public AuthToken authenticate(AuthTokenReq authReauest) {
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
    }
}

