package ir.dealit.restful.module.wallet.service.impl;

import ir.dealit.restful.dto.wallet.CreditCardInfo;
import ir.dealit.restful.dto.wallet.NewCreditCard;
import ir.dealit.restful.dto.wallet.WalletInfo;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.wallet.entity.AssetEntity;
import ir.dealit.restful.module.wallet.entity.BlockAssetEntity;
import ir.dealit.restful.module.wallet.entity.CreditCardEntity;
import ir.dealit.restful.module.wallet.entity.WalletEntity;
import ir.dealit.restful.module.wallet.repository.WalletRepository;
import ir.dealit.restful.module.wallet.service.TransactionService;
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
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final ExchangeRateCurrencyService exchangeRateCurrencyService;
    private final TransactionService transactionService;

    @Override
    public BigDecimal totalAssets(UserEntity user, CurrencyUnit target) {
        final List<Money> monies = user.getWallet().getMonies();
        return this.total(monies, target).getAmount();
    }

    @Override
    public BigDecimal totalBlockAssets(UserEntity user, CurrencyUnit target) {
        final List<Money> monies = user.getWallet().getBlockAssets()
                .stream()
                .map(BlockAssetEntity::getAsset)
                .map(AssetEntity::toMoney)
                .collect(Collectors.toList());
        return this.total(monies, target).getAmount();
    }

    @Override
    public BigDecimal balance(UserEntity user, CurrencyUnit target) {
        return this.totalAssets(user, target).subtract(this.totalBlockAssets(user, target));
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
    public WalletInfo walletInfo(UserEntity user) {
        CurrencyUnit unit = CurrencyUnit.of(user.getWallet().getDefaultCurrency().getCode());
        return WalletInfo.builder()
                .balance(this.balance(user, unit).doubleValue())
                .blockBalance(this.totalBlockAssets(user, unit).doubleValue())
                .totalBalance(this.totalAssets(user, unit).doubleValue())
                .totalIncome(transactionService.income(user, unit).doubleValue())
                .totalOutcome(transactionService.outcome(user, unit).doubleValue())
                .defaultCurrency(user.getWallet().getDefaultCurrency())
                .build();
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

    private Money total (List<Money> monies, CurrencyUnit target) {
        double total = 0d;
        for (Money money : monies) {
            var value = exchangeRateCurrencyService.convert(
                    money.getCurrencyUnit().getCode(),
                    target.getCode(),
                    money.getAmount().doubleValue());
            total += value;
        }
        return Money.of(target, total);
    }
}
