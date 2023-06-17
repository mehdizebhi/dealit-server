package ir.dealit.restful.api.user;

import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.dto.user.UserInfo;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/users")
public interface UserApiV1 {

    @GetMapping("")
    ResponseEntity<List<UserInfo>> getAllUsers();

    @PostMapping("")
    ResponseEntity<UserInfo> createUser (
            @RequestBody NewUser newUser
    );

    @GetMapping("/{id}")
    ResponseEntity<UserInfo> getUserById (
            @PathVariable("id") ObjectId id
    );

/*    @GetMapping("")
    ResponseEntity<UserInfo> getUserByUsername (
            @RequestParam(name = "username", required = true) String username
    );*/

}
