package ir.dealit.restful.module.account.service.impl;

import ir.dealit.restful.dto.account.*;
import ir.dealit.restful.dto.enums.*;
import ir.dealit.restful.dto.notification.Notification;
import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.module.account.entity.ClientAccountEntity;
import ir.dealit.restful.module.account.entity.FreelancerAccountEntity;
import ir.dealit.restful.module.account.service.AccountService;
import ir.dealit.restful.module.chat.service.ChatService;
import ir.dealit.restful.module.contract.service.ContractService;
import ir.dealit.restful.module.job.service.JobAdService;
import ir.dealit.restful.module.job.service.ProposalService;
import ir.dealit.restful.module.notification.service.NotificationService;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.service.UserAuthService;
import ir.dealit.restful.module.wallet.service.TransactionService;
import ir.dealit.restful.module.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserAuthService userAuthService;
    private final WalletService walletService;
    private final NotificationService notificationService;
    private final JobAdService jobAdService;
    private final ProposalService proposalService;
    private final TransactionService transactionService;
    private final ContractService contractService;
    private final ChatService chatService;

    @Override
    public AccountStats accountStats(UserEntity user) {
        AccountStats accountStats = AccountStats.builder()
                .balance(walletService.totalBalance(user))
                .newMessage(chatService.countNewMessage(user))
                .newTransaction(transactionService.countNewTransaction(user))
                .activeProposals(proposalService.count(ProposalStatus.ACTIVE, user))
                .activeJobs(contractService.countFreelancerContracts(ContractStatus.ACTIVE, user))
                .newInvitations(0)
                .newProposals(proposalService.countNewProposals(ProposalStatus.ACTIVE, user))
                .activeAds(jobAdService.countClientJobAds(JobAdStatus.ACTIVE, user))
                .income(transactionService.income(user, Currency.RIAL).doubleValue())
                .outcome(transactionService.outcome(user, Currency.RIAL).doubleValue())
                .build();

        return accountStats;
    }

    @Override
    public AccountInfo accountInfo(UserEntity user) {
        return new AccountInfo(user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getDisplayName(),
                user.getPictureHref(),
                getAccountTypes(user.getAccounts()));
    }

    @Override
    public AccountOverview accountOverview(UserEntity user) {
        var model = AccountOverview.builder()
                .displayName(user.getDisplayName())
                .email(user.getEmail())
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .balance(walletService.totalBalance(user))
                .connections(user.getConnections())
                .confirmedEmail(user.isEmailConfirmed())
                .confirmedPhone(user.isPhoneConfirmed())
                .types(getAccountTypes(user.getAccounts()))
                .createdAt(new DateTime(user.getCreatedAt()))
                .updatedAt(new DateTime(user.getUpdatedAt()))
                .build();

        return model;
    }

/*    @Override
    public TinyStats accountStats(UserEntity user) {
        var accountTypes = userAuthService.getAccountTypes(user);
        TinyStats.ClientTinyStats clientStats = null;
        TinyStats.FreelancerTinyStats freelancerStats = null;
        if (accountTypes.contains(AccountType.CLIENT)) {
            clientStats = TinyStats.ClientTinyStats.builder()
                    .activeAds(jobAdService.countClientJobAds(JobAdStatus.ACTIVE, user))
                    .newProposals(proposalService.countNewProposals(ProposalStatus.ACTIVE, user))
                    .outcome(transactionService.outcome(user, Currency.TOMAN).doubleValue())
                    .build();
        } else if (accountTypes.contains(AccountType.FREELANCER)) {
            freelancerStats = TinyStats.FreelancerTinyStats.builder()
                    .activeJobs(contractService.countFreelancerContracts(ContractStatus.ACTIVE, user))
                    .activeProposals(proposalService.count(ProposalStatus.ACTIVE, user))
                    .income(transactionService.income(user, Currency.TOMAN).doubleValue())
                    .newInvitations(0)
                    .totalConnection(user.getConnections())
                    .build();
        }
        var model = TinyStats.builder()
                .message("Your Tiny Stats here!")
                .balance(walletService.totalBalance(user).get())
                .notifications(notificationService.activeNotificationsByType(PageRequest.of(0,5), NotificationType.TINY, user))
                .client(clientStats)
                .freelancer(freelancerStats)
                .build();

        return model;
    }

    @Override
    public TinyStats.FreelancerTinyStats freelancerStats(UserEntity user) {
        var accountTypes = userAuthService.getAccountTypes(user);
        TinyStats.FreelancerTinyStats freelancerStats = null;
        if (accountTypes.contains(AccountType.FREELANCER)) {
            freelancerStats = TinyStats.FreelancerTinyStats.builder()
                    .activeJobs(contractService.countFreelancerContracts(ContractStatus.ACTIVE, user))
                    .activeProposals(proposalService.count(ProposalStatus.ACTIVE, user))
                    .income(transactionService.income(user, Currency.TOMAN).doubleValue())
                    .newInvitations(0)
                    .totalConnection(user.getConnections())
                    .build();
            return freelancerStats;
        }
        return null;
    }

    @Override
    public TinyStats.ClientTinyStats clientStats(UserEntity user) {
        var accountTypes = userAuthService.getAccountTypes(user);
        TinyStats.ClientTinyStats clientStats = null;
        if (accountTypes.contains(AccountType.CLIENT)) {
            clientStats = TinyStats.ClientTinyStats.builder()
                    .activeAds(jobAdService.countClientJobAds(JobAdStatus.ACTIVE, user))
                    .newProposals(proposalService.countNewProposals(ProposalStatus.ACTIVE, user))
                    .outcome(transactionService.outcome(user, Currency.TOMAN).doubleValue())
                    .build();
            return clientStats;
        }
        return null;
    }

    @Override
    public Page<Notification> notifications(Pageable pageable, UserEntity user) {
        return notificationService.activeNotificationsByType(pageable, NotificationType.TINY, user);
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
    }*/

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
