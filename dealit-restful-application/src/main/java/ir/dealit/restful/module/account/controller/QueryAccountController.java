package ir.dealit.restful.module.account.controller;

import static org.springframework.http.ResponseEntity.*;

import ir.dealit.restful.api.query.QueryAccountApi;
import ir.dealit.restful.dto.account.*;
import ir.dealit.restful.module.account.service.QueryAccountService;
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

    private final QueryAccountService queryAccountService;

    @Override
    public ResponseEntity<EntityModel<AccountOverview>> getAccountOverview(Authentication authentication) {
        return queryAccountService
                .accountOverview(authentication)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @Override
    public ResponseEntity<EntityModel<TinyStats>> getAccountStats(Authentication authentication) {
        return queryAccountService
                .accountStats(authentication)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @Override
    public ResponseEntity<EntityModel<WorkingHours>> getWorkingHours(Authentication authentication) {
        return queryAccountService
                .workingHours(authentication)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @Override
    public ResponseEntity<EntityModel<IncomeInfo>> getIncomeInfo(Authentication authentication) {
        return queryAccountService
                .incomeInfo(authentication)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @Override
    public ResponseEntity<EntityModel<AccountActivity>> getAccountActivity(Authentication authentication) {
        return queryAccountService
                .accountActivity(authentication)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }
}
