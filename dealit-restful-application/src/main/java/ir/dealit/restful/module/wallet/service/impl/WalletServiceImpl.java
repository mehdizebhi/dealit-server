package ir.dealit.restful.module.wallet.service.impl;

import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.service.UserAuthService;
import ir.dealit.restful.module.wallet.repository.WalletRepository;
import ir.dealit.restful.module.wallet.service.WalletService;
import ir.dealit.restful.service.ExchangeRateCurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
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
}
