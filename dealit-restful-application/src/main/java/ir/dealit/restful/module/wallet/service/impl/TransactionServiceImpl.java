package ir.dealit.restful.module.wallet.service.impl;

import ir.dealit.restful.dto.enums.Currency;
import ir.dealit.restful.dto.transaction.TransactionSummary;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.service.UserAuthService;
import ir.dealit.restful.module.wallet.entity.TransactionEntity;
import ir.dealit.restful.module.wallet.repository.TransactionRepository;
import ir.dealit.restful.module.wallet.service.AssetService;
import ir.dealit.restful.module.wallet.service.TransactionService;
import ir.dealit.restful.service.ExchangeRateCurrencyService;
import lombok.RequiredArgsConstructor;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserAuthService userAuthService;
    private final AssetService assetService;
    private final ExchangeRateCurrencyService exchangeRateCurrencyService;

    @Override
    public BigDecimal income(UserEntity user, CurrencyUnit target) {
        List<TransactionEntity> incomeTransactions = transactionRepository.findAllIncomeByWallet(user.getWallet().getId());
        return total(incomeTransactions, target);
    }

    @Override
    public BigDecimal income(DateTime start, DateTime end, UserEntity user, CurrencyUnit target) {
        List<TransactionEntity> incomeTransactions = transactionRepository.findAllIncomeTransactionByTime(
                start.toDate(), end.toDate(), user.getWallet().getId()
        );
        return total(incomeTransactions, target);
    }

    @Override
    public BigDecimal outcome(UserEntity user, CurrencyUnit target) {
        List<TransactionEntity> outcomeTransactions = transactionRepository.findAllOutcomeByWallet(user.getWallet().getId());
        return total(outcomeTransactions, target);
    }

    @Override
    public BigDecimal outcome(DateTime start, DateTime end, UserEntity user, CurrencyUnit target) {
        return null;
    }

    @Override
    public Integer countNewTransaction(UserEntity user) {
        return transactionRepository.countNewTransaction(user.getWallet().getId());
    }

    @Override
    public TransactionSummary summary(DateTime startTime, DateTime endTime, UserEntity user) {
 /*       List<TransactionEntity> transactions = transactionRepository.findAllIncomeTransactionByTime(startTime., endTime, user.getWallet().getId());
        List<TransactionEntity> previousTransactions;
        double total = 0d;
        double percentage = 0d;
        List<Double> amounts;

        for (var transaction : transactions) {
            total += transaction.getAmount().getBalance().doubleValue();
        }
//        return new TransactionSummary();
        return null;*/
        return null;
    }

    private BigDecimal total (List<TransactionEntity> transactions, CurrencyUnit target) {
        Money income = Money.of(target, 0d);
        for (var transaction : transactions) {
            if (transaction.getMoney().isSameCurrency(income)){
                income.plus(transaction.getMoney());
            } else {
                income.plus(exchangeRateCurrencyService.convert(transaction.getMoney().getCurrencyUnit().getCode(),
                        target.getCode(),
                        transaction.getMoney().getAmount().doubleValue()));
            }
        }
        return income.getAmount();
    }
}
