package ir.dealit.restful.module.account.service;

import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.module.account.entity.ClientAccountEntity;
import ir.dealit.restful.module.account.entity.FreelancerAccountEntity;
import ir.dealit.restful.module.account.repository.AccountRepository;
import ir.dealit.restful.module.account.repository.ClientAccountRepository;
import ir.dealit.restful.module.account.repository.FreelancerAccountRepository;
import ir.dealit.restful.module.chat.repository.ChatRepository;
import ir.dealit.restful.module.inbox.repository.InboxRepository;
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
        if (account instanceof FreelancerAccountEntity) {
            return freelancerAccountRepository.save((FreelancerAccountEntity) account);
        } else if (account instanceof ClientAccountEntity) {
            return clientAccountRepository.save((ClientAccountEntity) account);
        }
        return accountRepository.save(account);
    }

    public Optional<AccountEntity> findAccountById(ObjectId accountId) {
        return accountRepository.findById(accountId);
    }


}
