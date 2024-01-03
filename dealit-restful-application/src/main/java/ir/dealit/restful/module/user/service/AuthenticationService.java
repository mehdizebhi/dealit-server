package ir.dealit.restful.module.user.service;

import ir.dealit.restful.dto.auth.*;
import ir.dealit.restful.dto.enums.OTPSenderMechanism;
import ir.dealit.restful.dto.enums.VerifyOTPType;
import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.module.user.entity.ConfirmationCodeEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.repository.ConfirmationCodeRepository;
import ir.dealit.restful.module.user.repository.UserRepository;
import ir.dealit.restful.service.MailService;
import ir.dealit.restful.service.SMSService;
import ir.dealit.restful.util.exception.DealitException;
import ir.dealit.restful.util.exception.IncorrectPasswordException;
import ir.dealit.restful.util.exception.InvalidConfirmCodeException;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
    private final ConfirmationCodeService confirmationCodeService;
    private final MailService mailService;


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
        switch (senderMechanism) {
            case EMAIL -> mailService.send(user.getEmail(), "Verify Email", confirmationCodeService.newOTPCode("Verify Email", user));
            case SMS -> smsService.send(smsNumber, user.getPhoneNumber(), confirmationCodeService.newOTPCode("Verify Phone Number", user));
            default -> throw new DealitException("Invalid Sender Mechanism", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Transactional
    public void verifyOTPCode(String code, UserEntity user, VerifyOTPType verifyOTPType) {
        var confirmationCode = confirmationCodeRepository
                .findByCodeAndUserAndUsedAndExpireAtIsAfter(code, user, false, DateTime.now().toDate());

        if (confirmationCode.isPresent()) {
            confirmationCode.get().setUsed(true);
            confirmationCodeRepository.save(confirmationCode.get());
            switch (verifyOTPType) {
                case EMAIL -> user.setPhoneConfirmed(true);
                case PHONE_NUMBER -> user.setEmailConfirmed(true);
                default -> throw new DealitException("Invalid OTP Type", HttpStatus.NOT_ACCEPTABLE);
            }
            userRepository.save(user);
            return;
        }
        throw new InvalidConfirmCodeException(HttpStatus.NOT_FOUND);
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

