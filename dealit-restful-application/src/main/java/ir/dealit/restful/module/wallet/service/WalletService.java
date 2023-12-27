package ir.dealit.restful.module.wallet.service;

import ir.dealit.restful.dto.wallet.CreditCardInfo;
import ir.dealit.restful.dto.wallet.NewCreditCard;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.joda.money.CurrencyUnit;
import org.springframework.security.access.annotation.Secured;

import java.math.BigDecimal;

@Secured("ROLE_USER")
public interface WalletService {
    BigDecimal totalBalance(UserEntity user, CurrencyUnit target);

    CreditCardInfo creditCard(UserEntity user);

    void newCreditCard(NewCreditCard creditCard, UserEntity user);
}
