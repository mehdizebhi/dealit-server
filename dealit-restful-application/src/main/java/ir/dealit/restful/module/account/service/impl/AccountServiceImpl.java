package ir.dealit.restful.module.account.service.impl;

import ir.dealit.restful.dto.account.*;
import ir.dealit.restful.dto.enums.AccountType;
import ir.dealit.restful.dto.enums.JobAdStatus;
import ir.dealit.restful.dto.enums.NotificationType;
import ir.dealit.restful.dto.enums.ProposalStatus;
import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.module.account.entity.ClientAccountEntity;
import ir.dealit.restful.module.account.entity.FreelancerAccountEntity;
import ir.dealit.restful.module.account.repository.AccountRepository;
import ir.dealit.restful.module.account.service.QueryAccountService;
import ir.dealit.restful.module.job.service.JobAdService;
import ir.dealit.restful.module.job.service.ProposalService;
import ir.dealit.restful.module.notification.service.NotificationService;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.repository.UserRepository;
import ir.dealit.restful.module.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryAccountServiceImpl implements QueryAccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final WalletService walletService;
    private final NotificationService notificationService;
    private final JobAdService jobAdService;
    private final ProposalService proposalService;

    @Override
    public AccountOverview accountOverview(UserEntity user) {
        var model = AccountOverview.builder()
                .displayName(user.getDisplayName())
                .email(user.getEmail())
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .types(getAccountTypes(user.getAccounts()))
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();

        return model;
    }

    @Override
    public TinyStats accountStats(UserEntity user) {
        var model = TinyStats.builder()
                .message("Your Tiny Stats here!")
                .balance(walletService.totalBalance(user).get())
                .notifications(notificationService.activeNotificationsByType(PageRequest.of(0,5), user, NotificationType.TINY))
                .client(TinyStats.ClientTinyStats.builder()
                        .activeAds(jobAdService.count(JobAdStatus.ACTIVE, user))
                        .newProposals(proposalService.numberOfReceivedProposals(false, ProposalStatus.ACTIVE, user))
                        .outcome()
                        .build())
                .freelancer(TinyStats.FreelancerTinyStats.builder()
                        .activeJobs()
                        .activeProposals()
                        .income()
                        .newInvitations()
                        .totalConnection()
                        .build())
                .build();

        return model;
    }

    @Override
    public WorkingHours workingHours(UserEntity user) {
        var model = WorkingHours.builder()
                .build();

        return model;
    }

    @Override
    public IncomeInfo incomeInfo(UserEntity user) {
        var model = IncomeInfo.builder()
                .build();

        return model;
    }

    @Override
    public AccountActivity accountActivity(UserEntity user) {
        var model = AccountActivity.builder()
                .build();

        return model;
    }

    private List<AccountType> getAccountTypes(List<AccountEntity> accountEntities) {
        List<AccountType> types = new ArrayList<>();
        for (AccountEntity accountEntity : accountEntities) {
            if (accountEntity instanceof ClientAccountEntity) {
                types.add(AccountType.CLIENT);
            } else if (accountEntity instanceof FreelancerAccountEntity) {
                types.add(AccountType.FREELANCER);
            }
        }
        return types;
    }
}
