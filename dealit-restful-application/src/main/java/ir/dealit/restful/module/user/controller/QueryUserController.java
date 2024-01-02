package ir.dealit.restful.module.user.controller;

import ir.dealit.restful.api.query.QueryUserApi;
import ir.dealit.restful.dto.user.UserActivity;
import ir.dealit.restful.dto.user.UserInfo;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.service.UserService;
import ir.dealit.restful.util.hateoas.UserModelAssembler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class QueryUserController implements QueryUserApi {

    private final UserModelAssembler assembler;
    private final UserService userService;

    @Override
    public ResponseEntity<EntityModel<UserInfo>> getUserInfo(Authentication authentication) {
        return ResponseEntity.ok(assembler.toModel(authentication));
    }

    @Override
    public ResponseEntity<Page<UserActivity>> getUserActivities(Pageable pageable, Authentication authentication) {
        return ResponseEntity.ok(userService.activities((UserEntity) authentication.getPrincipal(), pageable));
    }
}