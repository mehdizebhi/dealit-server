package ir.dealit.restful.service.auth;

import ir.dealit.restful.config.security.jwt.util.JwtUtilsImpl;
import ir.dealit.restful.dto.auth.AuthenticationRequest;
import ir.dealit.restful.dto.auth.AuthenticationResponse;
import ir.dealit.restful.dto.auth.RegisterRequest;
import ir.dealit.restful.dto.auth.RegisterResponse;
import ir.dealit.restful.model.user.User;
import ir.dealit.restful.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilsImpl jwtUtils;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterRequest regRequest) {
        User user = User.builder()
                .username(regRequest.getUsername())
                .password(passwordEncoder.encode(regRequest.getPassword()))
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .enabled(true)
                .build();
        userRepository.save(user);
        return user != null ? RegisterResponse.builder().successfulRegister(true).build()
                : RegisterResponse.builder().successfulRegister(false).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authReauest) {
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
        Optional<User> user = userRepository.findByUsernameOptionally(authReauest.getUsername());
        return user.isPresent() ?
                AuthenticationResponse.builder()
                        .token(jwtUtils.generateToken(user.get()))
                        .build()
                : AuthenticationResponse.builder().token(null).build();
    }
}

