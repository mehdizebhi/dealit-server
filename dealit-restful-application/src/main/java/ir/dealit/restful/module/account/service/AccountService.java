package ir.dealit.restful.module.account.service;

import ir.dealit.restful.dto.account.*;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.springframework.security.access.annotation.Secured;

@Secured("ROLE_USER")
public interface QueryAccountService {
    AccountOverview accountOverview(UserEntity user);
    TinyStats accountStats(UserEntity user);
    WorkingHours workingHours(UserEntity user);
    IncomeInfo incomeInfo(UserEntity user);
    AccountActivity accountActivity(UserEntity user);
}
