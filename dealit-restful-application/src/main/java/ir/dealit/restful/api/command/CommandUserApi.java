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
import java.util.Map;

@RequestMapping("/v1/users")
public interface CommandUserApi {

    @PatchMapping(value = {"", "/"})
    ResponseEntity<ResponseModel<AuthToken>> partialUserUpdate(
            @RequestBody PartialUserUpdate userUpdate,
            Authentication authentication
    );

    @PostMapping("/picture")
    ResponseEntity<ResponseModel<Map<String, Object>>> updatePicture(
            @RequestPart MultipartFile file,
            Authentication authentication
    ) throws Exception;

    @DeleteMapping("/picture")
    ResponseEntity<ResponseModel<Void>> deletePicture(
            Authentication authentication
    ) throws Exception;

    @PatchMapping("/password")
    ResponseEntity<ResponseModel<Void>> updatePassword(
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
