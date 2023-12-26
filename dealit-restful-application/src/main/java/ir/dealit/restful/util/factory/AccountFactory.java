package ir.dealit.restful.util.factory;

import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.module.account.entity.ClientAccountEntity;
import ir.dealit.restful.module.account.entity.FreelancerAccountEntity;
import ir.dealit.restful.module.chat.entity.ChatEntity;
import ir.dealit.restful.module.inbox.entity.InboxEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.wallet.entity.AssetEntity;
import ir.dealit.restful.dto.enums.Currency;
import ir.dealit.restful.module.wallet.entity.WalletEntity;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;

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
        wallet.addMoney(Money.of(CurrencyUnit.of("IRR"), 0d));
        return wallet;
    }

    public static InboxEntity inbox(UserEntity owner) {
        return new InboxEntity(owner);
    }

    public static ChatEntity chat(UserEntity owner) {
        return new ChatEntity(owner);
    }
}
