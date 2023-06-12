package ir.dealit.restful.service.auth;

import ir.dealit.restful.config.security.jwt.util.JwtUtilsImpl;
import ir.dealit.restful.dto.auth.SignInReq;
import ir.dealit.restful.dto.auth.AuthTokenRes;
import ir.dealit.restful.dto.auth.UserSignUpReq;
import ir.dealit.restful.dto.auth.UserSignUpRes;
import ir.dealit.restful.entity.user.User;
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
        User user = User.builder()
                .username(regRequest.getUsername())
                .password(passwordEncoder.encode(regRequest.getPassword()))
                .displayName(regRequest.getDisplayName())
                .email(regRequest.getEmail())
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .enabled(true)
                .build();
        userRepository.save(user);
        return user != null ? UserSignUpRes.builder().successfulRegister(true).build()
                : UserSignUpRes.builder().successfulRegister(false).build();
    }

    public AuthTokenRes authenticate(SignInReq authReauest) {
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
        User user = userRepository.findByUsername(authReauest.getUsername());
        return user != null ? AuthTokenRes.builder()
                .token(jwtUtils.generateToken(user))
                .build()
                : AuthTokenRes.builder().token(null).build();
    }
}

