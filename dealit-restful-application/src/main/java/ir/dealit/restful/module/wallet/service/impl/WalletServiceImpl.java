package ir.dealit.restful.module.wallet.service.impl;

import ir.dealit.restful.dto.wallet.CreditCardInfo;
import ir.dealit.restful.dto.wallet.NewCreditCard;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.service.UserAuthService;
import ir.dealit.restful.module.wallet.entity.CreditCardEntity;
import ir.dealit.restful.module.wallet.entity.WalletEntity;
import ir.dealit.restful.module.wallet.repository.WalletRepository;
import ir.dealit.restful.module.wallet.service.WalletService;
import ir.dealit.restful.service.ExchangeRateCurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final UserAuthService userAuthService;
    private final WalletRepository walletRepository;
    private final ExchangeRateCurrencyService exchangeRateCurrencyService;

    @Override
    public BigDecimal totalBalance(UserEntity user, CurrencyUnit target) {
        final List<Money> monies = user.getWallet().getMonies();
        Money total = Money.of(target, 0d);
        for (Money money : monies) {
            total.plus(exchangeRateCurrencyService.convert(money.getCurrencyUnit().getCode(),
                    target.getCode(),
                    money.getAmount().doubleValue()));
        }
        return total.getAmount();
    }

    @Override
    public CreditCardInfo creditCard(UserEntity user) {
        WalletEntity wallet = walletRepository.findById(user.getWallet().getId()).get();
        CreditCardInfo cardInfo = CreditCardInfo.builder()
                .cardNumber(wallet.getCreditCard().getCardNumber())
                .ownerName(wallet.getCreditCard().getOwnerName())
                .expiredMonth(wallet.getCreditCard().getExpiredMonth())
                .expiredYear(wallet.getCreditCard().getExpiredYear())
                .type(wallet.getCreditCard().getType())
                .bank(wallet.getCreditCard().getBank())
                .iban(wallet.getCreditCard().getIban())
                .confirmed(wallet.getCreditCard().isConfirmed())
                .payable(wallet.getCreditCard().isPayable())
                .build();
        return cardInfo;
    }

    @Override
    public void newCreditCard(NewCreditCard creditCard, UserEntity user) {
        WalletEntity wallet = walletRepository.findById(user.getWallet().getId()).get();
        CreditCardEntity cardEntity = CreditCardEntity.builder().build();
        BeanUtils.copyProperties(creditCard, cardEntity);
        cardEntity.setConfirmed(true);
        cardEntity.setPayable(false);
        wallet.setCreditCard(cardEntity);
        walletRepository.save(wallet);
    }
}
