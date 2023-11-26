package ir.dealit.restful.api.command;

import ir.dealit.restful.dto.profile.ChangeProfile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/profiles")
public interface CommandProfileApi {

    @PutMapping("/")
    ResponseEntity<Void> updateProfile(
            @RequestBody ChangeProfile changeProfile,
            Authentication authentication
    );

    @PatchMapping("/")
    ResponseEntity<Void> partialUpdateProfile(
            @RequestBody ChangeProfile changeProfile,
            Authentication authentication
    );
}
