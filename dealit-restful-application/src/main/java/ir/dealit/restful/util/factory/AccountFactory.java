package ir.dealit.restful.util.factory;

import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.module.account.entity.ClientAccountEntity;
import ir.dealit.restful.module.account.entity.FreelancerAccountEntity;
import ir.dealit.restful.module.chat.entity.ChatEntity;
import ir.dealit.restful.module.inbox.entity.InboxEntity;
import ir.dealit.restful.module.wallet.entity.AssetEntity;
import ir.dealit.restful.module.wallet.entity.Currency;
import ir.dealit.restful.module.wallet.entity.WalletEntity;

import java.math.BigDecimal;
import java.util.Collections;

public class AccountFactory {

    public static AccountEntity account(String accountType) {
        switch (accountType) {
            case "freelancer":
                return new FreelancerAccountEntity(
                        Collections.emptyList(), Collections.emptyList(), Collections.emptyList()
                );
            case "client":
                return new ClientAccountEntity(Collections.emptyList(), Collections.emptyList());
            default:
                return new FreelancerAccountEntity();
        }
    }

    public static WalletEntity wallet(AccountEntity owner) {
        WalletEntity wallet = new WalletEntity(owner);
        wallet.addAsset(new AssetEntity(BigDecimal.ZERO, Currency.TOMAN));
        return wallet;
    }

    public static InboxEntity inbox(AccountEntity owner) {
        return new InboxEntity(owner);
    }

    public static ChatEntity chat(AccountEntity owner) {
        return new ChatEntity(owner);
    }
}
