package ir.dealit.restful.module.user.controller;

import ir.dealit.restful.api.query.QueryUserApi;
import ir.dealit.restful.dto.user.UserInfo;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.util.hateoas.UserModelAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QueryUserController implements QueryUserApi {

    private final UserModelAssembler assembler;

    @Override
    public ResponseEntity<EntityModel<UserInfo>> getUserInfo(Authentication authentication) {
        return ResponseEntity.ok(assembler.toModel(authentication));
    }
}
