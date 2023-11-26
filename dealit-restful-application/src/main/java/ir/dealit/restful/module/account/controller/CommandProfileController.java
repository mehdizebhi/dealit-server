package ir.dealit.restful.module.account.controller;

import ir.dealit.restful.api.command.CommandProfileApi;
import ir.dealit.restful.dto.profile.ChangeProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommandProfileController implements CommandProfileApi {
    @Override
    public ResponseEntity<Void> updateProfile(ChangeProfile changeProfile, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<Void> partialUpdateProfile(ChangeProfile changeProfile, Authentication authentication) {
        return null;
    }
}
