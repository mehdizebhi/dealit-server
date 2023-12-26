package ir.dealit.restful.module.wallet.service;

import ir.dealit.restful.dto.enums.Currency;
import ir.dealit.restful.dto.transaction.TransactionSummary;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.joda.money.CurrencyUnit;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TransactionService {

    BigDecimal income(UserEntity user, CurrencyUnit target);
    BigDecimal income(LocalDateTime start, LocalDateTime end, UserEntity user);
    BigDecimal outcome(UserEntity user, CurrencyUnit target);
    BigDecimal outcome(LocalDateTime start, LocalDateTime end, UserEntity user);
    Integer countNewTransaction(UserEntity user);
    TransactionSummary summary(DateTime startTime, DateTime endTime, UserEntity user);
}
