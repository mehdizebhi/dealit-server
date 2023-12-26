package ir.dealit.restful.module.user.controller;

import ir.dealit.restful.api.command.CommandUserApi;
import ir.dealit.restful.dto.auth.AuthToken;
import ir.dealit.restful.dto.user.PartialUserUpdate;
import ir.dealit.restful.dto.user.UpdateUser;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.service.TokenService;
import ir.dealit.restful.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class CommandUserController implements CommandUserApi {

    private final UserService userService;
    private final TokenService tokenService;

    @Override
    public ResponseEntity<AuthToken> partialUserUpdate(PartialUserUpdate userUpdate, Authentication authentication) {
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
        return ResponseEntity.ok(tokenService.renewAccessToken(tokenService.getRefreshToken((String) authentication.getCredentials())));
    }

    @Override
    public ResponseEntity<Void> updatePicture(MultipartFile file, Authentication authentication) throws Exception {
        return userService.updateProfilePicture(file, (UserEntity) authentication.getPrincipal())
                ? ResponseEntity.status(201).build() : ResponseEntity.status(400).build();
    }

    @Override
    public ResponseEntity<Void> deletePicture(Authentication authentication) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateUser(UpdateUser updateUserReq, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<Void> disableUser(Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<Void> enableUser(Authentication authentication) {
        return null;
    }
}