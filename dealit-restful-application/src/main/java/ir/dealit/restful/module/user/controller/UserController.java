package ir.dealit.restful.module.user.controller;

import static org.springframework.http.ResponseEntity.*;

import ir.dealit.restful.api.UserApi;
import ir.dealit.restful.dto.account.AccountOverview;
import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.dto.user.UserInfo;
import ir.dealit.restful.module.user.repository.UserRepository;
import ir.dealit.restful.util.hateoas.assembler.UserInfoRepresentationModelAssembler;
import ir.dealit.restful.module.user.service.UserDaoService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserDaoService service;
    private final UserInfoRepresentationModelAssembler assembler;
    private final UserRepository repository;

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

    @Override
    public ResponseEntity<AccountOverview> getAccountOverview(ObjectId userId) {
        return null;
    }

    /*@Override
    public ResponseEntity<List<String>> getAccountIds(ObjectId id) {
        return service.findAllAccountsByUserId(id)
//                .map(list -> list.stream().map(it -> it.toString()).collect(Collectors.toList()))
                .map(ResponseEntity::ok)
                .orElse(badRequest().build());
    }*/
}
