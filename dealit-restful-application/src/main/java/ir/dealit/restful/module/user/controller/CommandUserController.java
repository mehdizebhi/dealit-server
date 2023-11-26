package ir.dealit.restful.module.user.controller;

import ir.dealit.restful.api.command.CommandUserApi;
import ir.dealit.restful.dto.user.UpdateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommandUserController implements CommandUserApi {
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
