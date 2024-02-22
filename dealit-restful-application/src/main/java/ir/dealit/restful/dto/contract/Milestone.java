package ir.dealit.restful.dto.contract;

import lombok.Builder;
import org.joda.time.DateTime;

@Builder
public record Milestone(
        int step,
        Double ratePrice,
        String description,
        DateTime startedAt,
        DateTime endedAt
) {
}
