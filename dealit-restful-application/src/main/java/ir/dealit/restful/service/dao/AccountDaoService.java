package ir.dealit.restful.service.dao;

import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.repository.*;
import ir.dealit.restful.repository.entity.*;
import ir.dealit.restful.util.factory.AccountFactory;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
