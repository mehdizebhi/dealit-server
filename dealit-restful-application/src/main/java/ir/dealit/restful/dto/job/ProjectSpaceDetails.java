package ir.dealit.restful.dto.job;

import lombok.Builder;
import org.joda.time.DateTime;

import java.util.List;

@Builder
public record ProjectSpaceDetails(
        String id,
        String title,
        List<JobPosition> positions,
        DateTime createdAt,
        DateTime updatedAt
) {
}
