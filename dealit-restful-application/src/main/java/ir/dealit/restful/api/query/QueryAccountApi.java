package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.account.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/v1/accounts")
public interface QueryAccountApi {

    @GetMapping("/overview")
    ResponseEntity<EntityModel<AccountOverview>> getAccountOverview(
            Authentication authentication
    );

    @GetMapping("/stats")
    ResponseEntity<EntityModel<TinyStats>> getAccountStats(
            Authentication authentication
    );

    @GetMapping("/hours")
    ResponseEntity<EntityModel<WorkingHours>> getWorkingHours(
            Authentication authentication
    );

    @GetMapping("/income")
    ResponseEntity<EntityModel<IncomeInfo>> getIncomeInfo(
            Authentication authentication
    );

    @GetMapping("activities")
    ResponseEntity<EntityModel<AccountActivity>> getAccountActivity(
            Authentication authentication
    );
}
