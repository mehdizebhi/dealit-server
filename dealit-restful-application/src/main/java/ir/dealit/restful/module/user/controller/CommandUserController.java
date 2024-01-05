package ir.dealit.restful.module.user.controller;

import ir.dealit.restful.api.command.CommandUserApi;
import ir.dealit.restful.dto.auth.AuthToken;
import ir.dealit.restful.dto.auth.OTPCode;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.enums.OTPSenderMechanism;
import ir.dealit.restful.dto.enums.VerifyOTPType;
import ir.dealit.restful.dto.user.PartialUserUpdate;
import ir.dealit.restful.dto.user.UpdatePasswordForm;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.service.AuthenticationService;
import ir.dealit.restful.module.user.service.TokenService;
import ir.dealit.restful.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommandUserController implements CommandUserApi {

    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity<ResponseModel<AuthToken>> partialUserUpdate(PartialUserUpdate userUpdate, Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        if (userUpdate.username() != null) {
            userService.updateUsername(userUpdate.username(), user);
        }
        if (userUpdate.displayName() != null) {
            userService.updateDisplayName(userUpdate.displayName(), user);
        }
        if (userUpdate.email() != null) {
            userService.updateEmail(userUpdate.email(), user);
        }
        if (userUpdate.phoneNumber() != null) {
            userService.updatePhoneNumber(userUpdate.phoneNumber(), user);
        }
        return ResponseEntity.ok(
                new ResponseModel.Builder<AuthToken>()
                        .data(tokenService.renewAccessToken(tokenService.getRefreshToken((String) authentication.getCredentials())))
                        .success()
                        .build()
               );
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> updatePicture(MultipartFile file, Authentication authentication) throws Exception {
        userService.updateProfilePicture(file, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseModel.Builder<Void>().success().build());
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> deletePicture(Authentication authentication) {
        userService.deleteProfilePicture((UserEntity) authentication.getPrincipal());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseModel.Builder<Void>().success().build());
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> updatePassword(UpdatePasswordForm updatePasswordForm, Authentication authentication) {
        userService.updatePassword(updatePasswordForm.currentPassword(), updatePasswordForm.newPassword(), updatePasswordForm.confirmNewPassword(),
                (UserEntity) authentication.getPrincipal());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseModel.Builder<Void>().success().build());
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> sendSmsOTP(Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        if (!user.isPhoneConfirmed()) {
            authenticationService.sendOTP(user, OTPSenderMechanism.SMS);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseModel.Builder<Void>().success().build());
        }
        return ResponseEntity.badRequest().body(new ResponseModel.Builder<Void>().error("Phone Number is already verified").build());
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> sendEmailOTP(Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        if (!user.isEmailConfirmed()) {
            authenticationService.sendOTP(user, OTPSenderMechanism.EMAIL);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseModel.Builder<Void>().success().build());
        }
        return ResponseEntity.badRequest().body(new ResponseModel.Builder<Void>().error("Email is already verified").build());
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> verifySmsOTP(OTPCode otpCode, Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        if (!user.isPhoneConfirmed()) {
            authenticationService.verifyOTPCode(otpCode.code(), user, VerifyOTPType.PHONE_NUMBER);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseModel.Builder<Void>().success().build());
        }
        return ResponseEntity.badRequest().body(new ResponseModel.Builder<Void>().error("Phone Number is already verified").build());
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> verifyEmailOTP(OTPCode otpCode, Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        if (!user.isEmailConfirmed()) {
            authenticationService.verifyOTPCode(otpCode.code(), user, VerifyOTPType.EMAIL);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseModel.Builder<Void>().success().build());
        }
        return ResponseEntity.badRequest().body(new ResponseModel.Builder<Void>().error("Email is already verified").build());
    }
}
