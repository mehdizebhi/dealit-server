package ir.dealit.restful.dto.job;

import lombok.Builder;
import org.joda.time.DateTime;

@Builder
public record JobPosition(
        String id,
        String title,
        String projectSpaceTitle,
        String projectSpaceId,
        int jobAds,
        int contracts,
        DateTime createdAt,
        DateTime updatedAt
) {
}
