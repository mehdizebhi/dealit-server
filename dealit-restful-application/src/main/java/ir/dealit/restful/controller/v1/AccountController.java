package ir.dealit.restful.controller.v1;

import static org.springframework.http.ResponseEntity.*;

import ir.dealit.restful.controller.v1.api.AccountApi;
import ir.dealit.restful.dto.account.AccountOverview;
import ir.dealit.restful.service.dao.AccountDaoService;
import ir.dealit.restful.util.hateoas.assembler.AccountOverviewModelAssembler;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountApi {

    private final AccountDaoService service;
    private final AccountOverviewModelAssembler assembler;

    @Override
    public ResponseEntity<AccountOverview> getAccountOverview(ObjectId id) {
        return service.findAccountById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }
}
