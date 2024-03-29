package ir.dealit.restful.module.user.service;

import ir.dealit.restful.dto.user.UserActivity;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@Secured("ROLE_USER")
public interface UserService {

    String updateProfilePicture(MultipartFile img, UserEntity user) throws Exception;

    void deleteProfilePicture(UserEntity user);

    void updateUsername(String username, UserEntity user);

    void updateDisplayName(String displayName, UserEntity user);

    void updateEmail(String email, UserEntity user);

    void updatePhoneNumber(String phoneNumber, UserEntity user);

    void updatePassword(String oldPassword, String newPassword, String confirmNewPassword, UserEntity user);

    Page<UserActivity> activities(UserEntity user, Pageable pageable);
}
