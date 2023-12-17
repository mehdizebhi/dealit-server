package ir.dealit.restful.module.account.service;

import ir.dealit.restful.dto.account.*;
import ir.dealit.restful.dto.notification.Notification;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;

@Secured("ROLE_USER")
public interface AccountService {

    AccountStats accountStats(UserEntity user);
    AccountInfo accountInfo(UserEntity user);
    AccountOverview accountOverview(UserEntity user);
//    TinyStats accountStats(UserEntity user);
//    TinyStats.FreelancerTinyStats freelancerStats(UserEntity user);
//    TinyStats.ClientTinyStats clientStats(UserEntity user);
//    Page<Notification> notifications(Pageable pageable, UserEntity user);
//    WorkingHours workingHours(UserEntity user);
//    IncomeInfo incomeInfo(UserEntity user);
//    AccountActivity accountActivity(UserEntity user);
}
