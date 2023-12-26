package ir.dealit.restful.module.account.service.impl;

import ir.dealit.restful.dto.account.*;
import ir.dealit.restful.dto.enums.*;
import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.module.account.entity.ClientAccountEntity;
import ir.dealit.restful.module.account.entity.FreelancerAccountEntity;
import ir.dealit.restful.module.account.service.AccountService;
import ir.dealit.restful.module.contract.repository.ContractRepository;
import ir.dealit.restful.module.job.repository.ProposalRepository;
import ir.dealit.restful.module.project.repository.ProjectSpaceRepository;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final ContractRepository contractRepository;
    private final ProposalRepository proposalRepository;
    private final ProjectSpaceRepository projectSpaceRepository;

    @Override
    public FreelancerAccountInfo freelancerInfo(UserEntity user) {
        var info = FreelancerAccountInfo.builder()
                .contracts(contractRepository.countByHired(user.getId()))
                .activeContracts(contractRepository.countByStatusAndHired(ContractStatus.ACTIVE, user.getId()))
                .proposals(proposalRepository.countByOwner(user.getId()))
                .activeProposal(proposalRepository.countByStatusAndOwner(ProposalStatus.ACTIVE, user.getId()))
                .invitations(0)
                .build();

        return info;
    }

    @Override
    public ClientAccountInfo clientInfo(UserEntity user) {
        /*var info = ClientAccountInfo.builder()
                .projectSpaces(projectSpaceRepository.countByOwner(user.getId()))
                .activePositions(projectSpaceRepository.countJobPositionsByOwner(user.getId()))
                .contracts(contractRepository.countByHiredBy(user.getId()))
                .activeContracts(contractRepository.countByStatusAndHiredBy(ContractStatus.ACTIVE, user.getId()))
                .newProposal(proposalRepository.)
                .activeJobAd()
                .build();*/

        return null;
    }

 /*   @Override
    public AccountStats accountStats(UserEntity user) {
        AccountStats accountStats = AccountStats.builder()
                .balance(walletService.totalBalance(user, CurrencyUnit.of("IRR")).doubleValue())
                .newMessage(chatService.countNewMessage(user))
                .newTransaction(transactionService.countNewTransaction(user))
                .activeProposals(proposalService.count(ProposalStatus.ACTIVE, user))
                .activeJobs(contractService.countFreelancerContracts(ContractStatus.ACTIVE, user))
                .newInvitations(0)
                .newProposals(proposalService.countNewProposals(ProposalStatus.ACTIVE, user))
                .activeAds(jobAdService.countClientJobAds(JobAdStatus.ACTIVE, user))
                .income(transactionService.income(user, CurrencyUnit.of("IRR")).doubleValue())
                .outcome(transactionService.outcome(user, CurrencyUnit.of("IRR")).doubleValue())
                .build();

        return accountStats;
    }*/

    /*@Override
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
                .connections(user.getConnections())
                .confirmedEmail(user.isEmailConfirmed())
                .confirmedPhone(user.isPhoneConfirmed())
                .types(getAccountTypes(user.getAccounts()))
                .pictureHref(user.getPictureHref())
                .createdAt(new DateTime(user.getCreatedAt()))
                .updatedAt(new DateTime(user.getUpdatedAt()))
                .build();

        return model;
    }
*/

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
