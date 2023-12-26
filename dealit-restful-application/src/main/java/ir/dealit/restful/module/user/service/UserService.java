package ir.dealit.restful.module.user.service;

import ir.dealit.restful.module.user.entity.UserEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.multipart.MultipartFile;

@Secured("ROLE_USER")
public interface UserService {

    boolean updateProfilePicture(MultipartFile img, UserEntity user) throws Exception;

    void updateUsername(String username, UserEntity user);

    void updateDisplayName(String displayName, UserEntity user);

    void updateEmail(String email, UserEntity user);

    void updatePhoneNumber(String phoneNumber, UserEntity user);

    void updatePassword(String oldPassword, String newPassword, String confirmNewPassword, UserEntity user);

    void resetPassword(String resetToken, String newPassword, String confirmNewPassword, UserEntity user);
}
