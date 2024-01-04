package ir.dealit.restful.api.command;

import ir.dealit.restful.dto.auth.AuthToken;
import ir.dealit.restful.dto.auth.OTPCode;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.user.PartialUserUpdate;
import ir.dealit.restful.dto.user.UpdatePasswordForm;
import ir.dealit.restful.dto.user.UpdateUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/v1/users")
public interface CommandUserApi {

    @PatchMapping(value = {"", "/"})
    ResponseEntity<AuthToken> partialUserUpdate(
            @RequestBody PartialUserUpdate userUpdate,
            Authentication authentication
    );

    @PostMapping("/picture")
    ResponseEntity<Void> updatePicture(
            @RequestPart MultipartFile file,
            Authentication authentication
    ) throws Exception;

    @DeleteMapping("/picture")
    ResponseEntity<Void> deletePicture(
            Authentication authentication
    ) throws Exception;

    @PatchMapping("/disable")
    ResponseEntity<Void> disableUser(
            Authentication authentication
    );

    @PatchMapping("/enable")
    ResponseEntity<Void> enableUser(
            Authentication authentication
    );

    @PatchMapping("/password")
    ResponseEntity<Void> updatePassword(
            @RequestBody UpdatePasswordForm updatePasswordForm,
            Authentication authentication
    );

    @PostMapping("/sms-otp")
    ResponseEntity<ResponseModel<Void>> sendSmsOTP(
            Authentication authentication
    );

    @PostMapping("/email-otp")
    ResponseEntity<ResponseModel<Void>> sendEmailOTP(
            Authentication authentication
    );

    @PostMapping("/verify-sms-otp")
    ResponseEntity<ResponseModel<Void>> verifySmsOTP(
            @RequestBody OTPCode otpCode,
            Authentication authentication
    );

    @PostMapping("/verify-email-otp")
    ResponseEntity<ResponseModel<Void>> verifyEmailOTP(
            @RequestBody OTPCode otpCode,
            Authentication authentication
    );
}
