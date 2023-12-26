package ir.dealit.restful.module.wallet.service;

import ir.dealit.restful.dto.wallet.Asset;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import ir.dealit.restful.dto.enums.Currency;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Secured("ROLE_USER")
public interface WalletService {
    BigDecimal totalBalance(UserEntity user, CurrencyUnit target);
}
