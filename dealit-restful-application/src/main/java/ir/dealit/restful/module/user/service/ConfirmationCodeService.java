package ir.dealit.restful.module.user.service;

import ir.dealit.restful.module.user.entity.UserEntity;

public interface ConfirmationCodeService {
    String newOTPCode(String reason, UserEntity user);

    String newResetPasswordToken(UserEntity user);
}
