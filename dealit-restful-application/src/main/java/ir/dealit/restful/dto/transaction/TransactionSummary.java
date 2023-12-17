package ir.dealit.restful.dto.transaction;

import lombok.Builder;

import java.util.List;

@Builder
public record TransactionSummary(
        double total,
        double percentage,
        List<Double> amountOfDays
) {
}
