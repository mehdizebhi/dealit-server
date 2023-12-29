package ir.dealit.restful.module.user.service;

import ir.dealit.restful.dto.auth.*;
import ir.dealit.restful.dto.enums.OTPSenderMechanism;
import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.module.user.entity.ConfirmationCodeEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.repository.ConfirmationCodeRepository;
import ir.dealit.restful.module.user.repository.UserRepository;
import ir.dealit.restful.service.SMSService;
import ir.dealit.restful.util.exception.IncorrectPasswordException;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDaoService service;
    private final SMSService smsService;
    private final ConfirmationCodeRepository confirmationCodeRepository;
    private final UserRepository userRepository;
    private final TokenService tokenService;


    @Value("${services.sms.number}")
    private String smsNumber;

    public Optional<SignedInUser> register(NewUser newUser) {
        if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
            throw new IncorrectPasswordException("Your password and confirm password is not match.");
        }
        Optional<UserEntity> userEntity = service.registerUser(newUser);
        return userEntity.map(u -> {
            return Optional.of(SignedInUser.builder()
                    .username(u.getUsername())
                    .userId(u.getId().toString())
                    .token(tokenService.createToken(userEntity.get()))
                    .build()
            );
        }).orElse(Optional.empty());
    }

    public Optional<SignedInUser> authenticate(AuthTokenRequest req) {
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
                        .token(tokenService.createToken(user))
                        .build()
        ) : Optional.empty();

    }

    public void logout(String token){
        tokenService.expireToken(token);
    }

    public void sendOTP(UserEntity user, OTPSenderMechanism senderMechanism) {
        int number = new Random().nextInt(999999);
        String code = String.format("%06d", number);
        var confirmationCode = ConfirmationCodeEntity.builder()
                .code(code)
                .expireAt(DateTime.now().plusMinutes(5).toDate())
                .reason("Verify Phone Number")
                .user(user)
                .used(false)
                .build();
        confirmationCodeRepository.save(confirmationCode);
        if (senderMechanism == OTPSenderMechanism.EMAIL) {
            // TODO: Implement Email Sender for sending email to user
        } else {
            smsService.send(smsNumber, user.getPhoneNumber(), code);
        }
    }

    @Transactional
    public boolean verifyOTPCode(String code, UserEntity user) {
        var confirmationCode = confirmationCodeRepository
                .findByCodeAndUserAndUsedAndExpireAtIsAfter(code, user, false, DateTime.now().toDate());

        if (confirmationCode.isPresent()) {
            confirmationCode.get().setUsed(true);
            confirmationCodeRepository.save(confirmationCode.get());
            user.setPhoneConfirmed(true);
            userRepository.save(user);
            return true;
        }
        return false;
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

/*    public AuthToken authenticate(AuthTokenRequest authReauest) {
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

