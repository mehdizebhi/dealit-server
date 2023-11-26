package ir.dealit.restful.module.account.controller;

import static org.springframework.http.ResponseEntity.*;

import ir.dealit.restful.api.AccountApi;
import ir.dealit.restful.dto.account.AccountOverview;
import ir.dealit.restful.module.account.service.AccountDaoService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountApi {

    private final AccountDaoService service;

    @Override
    public ResponseEntity<AccountOverview> getAccountOverview(ObjectId id) {
        return null;
    }

    /*@Override
    public ResponseEntity<AccountOverview> getAccountOverview(ObjectId id) {
        return service.findAccountById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }*/
}
