package ir.dealit.restful.dto.project;

import lombok.Builder;
import org.joda.time.DateTime;

@Builder
public record ProjectSpace(
        String id,
        String title,
        int positions,
        int activeContracts,
        long totalWorkTimeInSeconds,
        int members,
        double totalPayment,
        DateTime createdAt,
        DateTime updatedAt
) {
}
