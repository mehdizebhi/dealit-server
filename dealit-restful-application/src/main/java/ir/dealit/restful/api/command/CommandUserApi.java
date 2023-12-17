package ir.dealit.restful.api.command;

import ir.dealit.restful.dto.user.UpdateUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/v1/users")
public interface CommandUserApi {

    @PostMapping("/picture")
    ResponseEntity<Void> updatePicture(
            @RequestPart MultipartFile file,
            Authentication authentication
    ) throws Exception;

    @DeleteMapping("/picture")
    ResponseEntity<Void> deletePicture(
            Authentication authentication
    ) throws Exception;

    @PutMapping("/")
    ResponseEntity<Void> updateUser(
            @RequestBody UpdateUser updateUserReq,
            Authentication authentication
    );

    @PatchMapping("/disable")
    ResponseEntity<Void> disableUser(
            Authentication authentication
    );

    @PatchMapping("/enable")
    ResponseEntity<Void> enableUser(
            Authentication authentication
    );
}
