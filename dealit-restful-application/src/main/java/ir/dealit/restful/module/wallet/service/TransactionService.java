package ir.dealit.restful.module.wallet.service;

import ir.dealit.restful.dto.transaction.TransactionSummary;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.joda.money.CurrencyUnit;
import org.joda.time.DateTime;
import org.springframework.security.access.annotation.Secured;

import java.math.BigDecimal;

@Secured("ROLE_USER")
public interface TransactionService {

    BigDecimal income(UserEntity user, CurrencyUnit target);
    BigDecimal income(DateTime start, DateTime end, UserEntity user, CurrencyUnit target);
    BigDecimal outcome(UserEntity user, CurrencyUnit target);
    BigDecimal outcome(DateTime start, DateTime end, UserEntity user, CurrencyUnit target);
    Integer countNewTransaction(UserEntity user);
    TransactionSummary summary(DateTime startTime, DateTime endTime, UserEntity user);
}
