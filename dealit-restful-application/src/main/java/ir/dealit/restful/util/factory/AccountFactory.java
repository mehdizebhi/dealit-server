package ir.dealit.restful.util.factory;

import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.module.account.entity.ClientAccountEntity;
import ir.dealit.restful.module.account.entity.FreelancerAccountEntity;
import ir.dealit.restful.module.account.entity.FreelancerProfileEntity;
import ir.dealit.restful.module.chat.entity.ChatEntity;
import ir.dealit.restful.module.inbox.entity.InboxEntity;
import ir.dealit.restful.module.job.entity.JobSpaceEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.wallet.entity.AssetEntity;
import ir.dealit.restful.dto.enums.Currency;
import ir.dealit.restful.module.wallet.entity.WalletEntity;

import java.math.BigDecimal;
import java.util.Collections;

public class AccountFactory {

    public static AccountEntity account(String accountType) {
        switch (accountType) {
            case "client":
                return new ClientAccountEntity();
            case "freelancer":
            default:
                return new FreelancerAccountEntity();
        }
    }

    public static WalletEntity wallet(UserEntity owner) {
        WalletEntity wallet = new WalletEntity(owner);
        wallet.addAsset(new AssetEntity(BigDecimal.ZERO, Currency.TOMAN));
        return wallet;
    }

    public static InboxEntity inbox(UserEntity owner) {
        return new InboxEntity(owner);
    }

    public static ChatEntity chat(UserEntity owner) {
        return new ChatEntity(owner);
    }
}
