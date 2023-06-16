package ir.dealit.restful.controller.v1.user;

import ir.dealit.restful.api.user.UserApiV1;
import ir.dealit.restful.dto.user.UserInfo;
import ir.dealit.restful.service.user.UserDaoService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApiV1 {

    private final UserDaoService userDaoService;

//    @GetMapping("/{username}")
//    public ResponseEntity<UserDetails> getUserById(
//            @PathVariable("username") String username
//    ) {
//        return ResponseEntity.ok(userDaoService.loadUserByUsername(username));
//    }
//
//    @GetMapping("")
//    public ResponseEntity<List<UserEntity>> getUsers() {
//        return ResponseEntity.ok(userDaoService.loadAllUsers());
//    }
//
//    @PostMapping("")
//    public ResponseEntity<ObjectId> createUser(
//            @RequestBody UserEntity userEntity
//    ) {
//        return ResponseEntity.ok(userDaoService.save(userEntity).getId());
//    }

    @Override
    public ResponseEntity<List<UserInfo>> getAllUsers() {
        return ResponseEntity.ok(userDaoService.loadAllUsers());
    }

    @Override
    public ResponseEntity<ObjectId> createUser(UserInfo user) {
        return ResponseEntity.ok(userDaoService.save(userEntity).getId());
    }

    @Override
    public ResponseEntity<UserInfo> getUserById(ObjectId id) {
        return ResponseEntity.ok(userDaoService.loadUserByUsername(username));
    }
}
