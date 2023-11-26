package ir.dealit.restful.module.account.service;

import ir.dealit.restful.dto.account.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;

import java.util.Optional;

@Secured("ROLE_USER")
public interface QueryAccountService {
    Optional<EntityModel<AccountOverview>> accountOverview(Authentication authentication);
    Optional<EntityModel<TinyStats>> accountStats(Authentication authentication);
    Optional<EntityModel<WorkingHours>> workingHours(Authentication authentication);
    Optional<EntityModel<IncomeInfo>> incomeInfo(Authentication authentication);
    Optional<EntityModel<AccountActivity>> accountActivity(Authentication authentication);
}
