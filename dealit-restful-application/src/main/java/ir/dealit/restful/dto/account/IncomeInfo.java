package ir.dealit.restful.dto.account;

import ir.dealit.restful.dto.enums.Currency;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class IncomeInfo {
    private double totalIncome;
    private Currency currency;
    private LocalDateTime start;
    private LocalDateTime end;
}
