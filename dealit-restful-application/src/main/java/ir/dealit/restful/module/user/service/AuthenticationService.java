package ir.dealit.restful.module.user.service;

import ir.dealit.restful.dto.auth.*;
import ir.dealit.restful.dto.enums.OTPSenderMechanism;
import ir.dealit.restful.dto.enums.VerifyOTPType;
import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.dto.user.ResetPassword;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.repository.ConfirmationCodeRepository;
import ir.dealit.restful.module.user.repository.UserRepository;
import ir.dealit.restful.service.MailService;
import ir.dealit.restful.service.SMSService;
import ir.dealit.restful.util.constant.HTMLEmailTemplate;
import ir.dealit.restful.util.exception.*;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

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
    private final PasswordEncoder bCryptPasswordEncoder;


    @Value("${services.sms.number}")
    private String smsNumber;

    @Value("${app.frontend.url}")
    private String frontendUrl;

    public SignedInUser register(NewUser newUser) {
        if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
            throw new IncorrectPasswordException(HttpStatus.NOT_ACCEPTABLE);
        }
        Optional<UserEntity> userOp = service.registerUser(newUser);
        if (!userOp.isPresent()) {
            throw new UserNotFoundException(HttpStatus.UNAUTHORIZED);
        }
        return SignedInUser.builder()
                .username(userOp.get().getUsername())
                .userId(userOp.get().getId().toString())
                .token(tokenService.createToken(userOp.get()))
                .build();
    }

    public SignedInUser authenticate(AuthTokenRequest req) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getUsername(),
                            req.getPassword()
                    )
            );
        } catch (Exception exp) {
            throw new InvalidCredentialsException(HttpStatus.UNAUTHORIZED);
        }
        UserEntity user = service.findUserByUsername(req.getUsername());
        if (Objects.nonNull(user)) {
            return SignedInUser.builder()
                    .userId(user.getId().toString())
                    .username(user.getUsername())
                    .token(tokenService.createToken(user))
                    .build();
        }
        throw new UserNotFoundException(HttpStatus.UNAUTHORIZED);
    }

    public void logout(String token){
        tokenService.expireToken(token);
    }

    public void sendOTP(UserEntity user, OTPSenderMechanism senderMechanism) {

        switch (senderMechanism) {
            case EMAIL -> {
                var codeList = confirmationCodeRepository.findAnyValidCode(user.getId(), VerifyOTPType.EMAIL.name(), DateTime.now().toDate());
                if (!codeList.isEmpty()) {
                    throw new TooManyRequestException("OTP already send to email");
                }
                mailService.send(user.getEmail(), "Verify Email", confirmationCodeService.newOTPCode(VerifyOTPType.EMAIL.name(), user));
            }
            case SMS -> {
                var codeList = confirmationCodeRepository.findAnyValidCode(user.getId(), VerifyOTPType.PHONE_NUMBER.name(), DateTime.now().toDate());
                if (!codeList.isEmpty()) {
                    throw new TooManyRequestException("OTP already send to phone");
                }
                smsService.send(smsNumber, user.getPhoneNumber(), confirmationCodeService.newOTPCode(VerifyOTPType.PHONE_NUMBER.name(), user));
            }
            default -> throw new DealitException("Invalid Sender Mechanism", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Transactional
    public void verifyOTPCode(String code, UserEntity user, VerifyOTPType verifyOTPType) {
        var confirmationCode = confirmationCodeRepository
                .findByCodeAndUserAndUsedAndReasonAndExpireAtIsAfter(code, user, false, verifyOTPType.name(), DateTime.now().toDate());

        if (confirmationCode.isPresent()) {
            confirmationCode.get().setUsed(true);
            confirmationCodeRepository.save(confirmationCode.get());
            switch (verifyOTPType) {
                case EMAIL -> user.setEmailConfirmed(true);
                case PHONE_NUMBER -> user.setPhoneConfirmed(true);
                default -> throw new DealitException("Invalid OTP Type", HttpStatus.NOT_ACCEPTABLE);
            }
            userRepository.save(user);
            return;
        }
        throw new InvalidConfirmCodeException(HttpStatus.NOT_FOUND);
    }

    public void sendForgetPasswordToken(@Email String email) {
        var userOp = userRepository.findByEmail(email);
        if (userOp.isPresent()) {
            String token = confirmationCodeService.newResetPasswordToken(userOp.get());
            mailService.send(email, "Reset Password",
                    HTMLEmailTemplate.getResetPasswordTemplate(frontendUrl + "/reset-password?token=" + token));
            return;
        }
        throw new UserNotFoundException(HttpStatus.NOT_FOUND);
    }

    public boolean checkResetPasswordTokenIsExist(String token) {
        var confirmationCode = confirmationCodeRepository
                .findByCodeAndUsedAndExpireAtIsAfter(token, false, DateTime.now().toDate());
        return confirmationCode.isPresent();
    }

    @Transactional
    public void verifyResetTokenAndUpdatePassword(String token, ResetPassword resetPassword) {
        var confirmationCode = confirmationCodeRepository
                .findByCodeAndUsedAndExpireAtIsAfter(token, false, DateTime.now().toDate());

        if (confirmationCode.isPresent()) {
            confirmationCode.get().setUsed(true);
            confirmationCodeRepository.save(confirmationCode.get());

            if (resetPassword.password().equals(resetPassword.confirmPassword())) {
                var user = userRepository.findById(confirmationCode.get().getUser().getId()).get();
                user.setPassword(bCryptPasswordEncoder.encode(resetPassword.password()));
                userRepository.save(user);
                return;
            }
            throw new InvalidPasswordException(HttpStatus.NOT_ACCEPTABLE);

        }
        throw new InvalidConfirmCodeException(HttpStatus.NOT_FOUND);
    }
}

