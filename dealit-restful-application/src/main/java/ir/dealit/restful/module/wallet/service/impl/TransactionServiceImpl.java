package ir.dealit.restful.module.wallet.service.impl;

import ir.dealit.restful.dto.enums.Currency;
import ir.dealit.restful.dto.transaction.TransactionSummary;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.service.UserAuthService;
import ir.dealit.restful.module.wallet.entity.TransactionEntity;
import ir.dealit.restful.module.wallet.repository.TransactionRepository;
import ir.dealit.restful.module.wallet.service.AssetService;
import ir.dealit.restful.module.wallet.service.TransactionService;
import lombok.RequiredArgsConstructor;
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

    @Override
    public BigDecimal income(UserEntity user, Currency target) {
        var walletId = userAuthService.getWalletId(user.getId());
        if (walletId.isPresent()) {
            var transactions = transactionRepository.findAllIncomeByWallet(walletId.get());
            BigDecimal income = new BigDecimal(0);
            transactions.forEach(transaction -> income.add(assetService.convert(transaction.getAmount(), target)));
            return income;
        }
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal income(LocalDateTime start, LocalDateTime end, UserEntity user) {
        return null;
    }

    @Override
    public BigDecimal outcome(UserEntity user, Currency target) {
        var walletId = userAuthService.getWalletId(user.getId());
        if (walletId.isPresent()) {
            var transactions = transactionRepository.findAllOutcomeByWallet(walletId.get());
            BigDecimal outcome = new BigDecimal(0);
            transactions.forEach(transaction -> outcome.add(assetService.convert(transaction.getAmount(), target)));
            return outcome;
        }
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal outcome(LocalDateTime start, LocalDateTime end, UserEntity user) {
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
}
