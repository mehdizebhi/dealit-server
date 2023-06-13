package ir.dealit.restful.api.v1.user;

import ir.dealit.restful.dto.user.UserInfo;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/users")
public interface UserApi {

    @GetMapping("")
    ResponseEntity<List<UserInfo>> getAllUsers();

    @PostMapping("")
    ResponseEntity<ObjectId> createUser (
            @RequestBody UserInfo user
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
