package ir.dealit.restful.controller.user;

import ir.dealit.restful.entity.user.User;
import ir.dealit.restful.service.user.UserDaoService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDaoService userDaoService;

    @GetMapping("/{username}")
    public ResponseEntity<UserDetails> getUserById(
            @PathVariable("username") String username
    ) {
        return ResponseEntity.ok(userDaoService.loadUserByUsername(username));
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userDaoService.loadAllUsers());
    }

    @PostMapping("")
    public ResponseEntity<ObjectId> createUser(
            @RequestBody User user
    ) {
        return ResponseEntity.ok(userDaoService.save(user).getId());
    }

}
