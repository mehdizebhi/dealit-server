package ir.dealit.restful.module.account.service;

import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.module.account.entity.ClientAccountEntity;
import ir.dealit.restful.module.account.entity.FreelancerAccountEntity;
import ir.dealit.restful.module.account.entity.FreelancerProfileEntity;
import ir.dealit.restful.module.account.repository.AccountRepository;
import ir.dealit.restful.module.account.repository.ClientAccountRepository;
import ir.dealit.restful.module.account.repository.FreelancerAccountRepository;
import ir.dealit.restful.module.account.repository.FreelancerProfileRepository;
import ir.dealit.restful.module.chat.repository.ChatRepository;
import ir.dealit.restful.module.inbox.repository.InboxRepository;
import ir.dealit.restful.module.job.entity.JobSpaceEntity;
import ir.dealit.restful.module.job.repository.JobSpaceRepository;
import ir.dealit.restful.module.project.entity.ProjectSpaceEntity;
import ir.dealit.restful.module.project.repository.ProjectSpaceRepository;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.repository.UserRepository;
import ir.dealit.restful.module.wallet.repository.WalletRepository;
import ir.dealit.restful.util.factory.AccountFactory;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AccountDaoService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final ClientAccountRepository clientAccountRepository;
    private final FreelancerAccountRepository freelancerAccountRepository;
    private final WalletRepository walletRepository;
    private final InboxRepository inboxRepository;
    private final ChatRepository chatRepository;
    private final JobSpaceRepository jobSpaceRepository;
    private final FreelancerProfileRepository profileRepository;
    private final ProjectSpaceRepository projectSpaceRepository;

//    public AccountEntity registerAccount() {
//        newAccount.setUser(userEntity);
//    }


    @Transactional
    public AccountEntity setupAccount(UserEntity userEntity, String accountType) {
        AccountEntity account = AccountFactory.account(accountType);
        account.setUser(userEntity);
        account = accountRepository.save(account);
        account.setWallet(walletRepository.save(AccountFactory.wallet(account)));
        account.setInbox(inboxRepository.save(AccountFactory.inbox(account)));
        account.setChat(chatRepository.save(AccountFactory.chat(account)));
        if (account instanceof FreelancerAccountEntity freelancer) {
            freelancer.setJobSpace(jobSpaceRepository.save(new JobSpaceEntity(account)));
            freelancer.setProfile(profileRepository.save(new FreelancerProfileEntity(account)));
            return freelancerAccountRepository.save(freelancer);
        } else if (account instanceof ClientAccountEntity client) {
            client.setProjectSpaces(Collections.singletonList(projectSpaceRepository.save(new ProjectSpaceEntity(account, "General"))));
            return clientAccountRepository.save(client);
        }
        return accountRepository.save(account);
    }

    public Optional<AccountEntity> findAccountById(ObjectId accountId) {
        return accountRepository.findById(accountId);
    }


}
