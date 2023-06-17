package ir.dealit.restful.controller.v1.user;

import static org.springframework.http.ResponseEntity.*;

import ir.dealit.restful.api.user.UserApiV1;
import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.dto.user.UserInfo;
import ir.dealit.restful.entity.user.UserEntity;
import ir.dealit.restful.hateoas.assembler.UserInfoRepresentationModelAssembler;
import ir.dealit.restful.service.user.UserDaoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApiV1 {

    private final UserDaoServiceImpl service;
    private final UserInfoRepresentationModelAssembler assembler;

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
        return ok(assembler.toListModel(service.findAllUsers()));
    }

    @Override
    public ResponseEntity<UserInfo> createUser(NewUser newUser) {
        return service.registerUser(newUser)
                .map(assembler::toModel)
                .map(model -> new ResponseEntity(model, HttpStatus.CREATED))
                .orElse(badRequest().build());
    }

    @Override
    public ResponseEntity<UserInfo> getUserById(ObjectId id) {
        return service.findUserById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }
}
