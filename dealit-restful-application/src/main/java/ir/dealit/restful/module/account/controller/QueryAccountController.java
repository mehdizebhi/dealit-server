package ir.dealit.restful.module.account.controller;

import ir.dealit.restful.api.query.QueryAccountApi;
import ir.dealit.restful.dto.account.*;
import ir.dealit.restful.module.account.service.AccountService;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QueryAccountController implements QueryAccountApi {

    private final AccountService accountService;

    @Override
    public ResponseEntity<EntityModel<AccountInfo>> getAccountInfo(Authentication authentication) {
        return ResponseEntity.ok(EntityModel.of(
                accountService.accountInfo((UserEntity) authentication.getPrincipal())));
    }

    @Override
    public ResponseEntity<EntityModel<AccountStats>> getAccountStats(Authentication authentication) {
        var model = EntityModel.of(accountService.accountStats((UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(model);
    }

    @Override
    public ResponseEntity<EntityModel<AccountOverview>> getAccountOverview(Authentication authentication) {
        var model = EntityModel.of(accountService
                .accountOverview((UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(model);
    }

    /*@Override
    public ResponseEntity<EntityModel<TinyStats.FreelancerTinyStats>> getFreelancerStats(Authentication authentication) {
        var model = EntityModel.of(accountService
                .freelancerStats((UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(model);
    }

    @Override
    public ResponseEntity<EntityModel<TinyStats.ClientTinyStats>> getClientStats(Authentication authentication) {
        var model = EntityModel.of(accountService
                .clientStats((UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(model);
    }

    @Override
    public ResponseEntity<PagedModel<Notification>> getNotifications(Authentication authentication) {
        var models = toPagedModel(accountService
                .notifications(PageRequest.of(0, 5), (UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(models);
    }

    @Override
    public ResponseEntity<EntityModel<TinyStats>> getAccountStats(Authentication authentication) {
        var model = EntityModel.of(accountService
                .accountStats((UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(model);
    }

    @Override
    public ResponseEntity<EntityModel<WorkingHours>> getWorkingHours(Authentication authentication) {
        var model = EntityModel.of(accountService
                .workingHours((UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(model);
    }

    @Override
    public ResponseEntity<EntityModel<IncomeInfo>> getIncomeInfo(Authentication authentication) {
        var model = EntityModel.of(accountService
                .incomeInfo((UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(model);
    }

    @Override
    public ResponseEntity<EntityModel<AccountActivity>> getAccountActivity(Authentication authentication) {
        var model = EntityModel.of(accountService
                .accountActivity((UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(model);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAccountConnections(Authentication authentication) {
        return null;
    }*/
}
