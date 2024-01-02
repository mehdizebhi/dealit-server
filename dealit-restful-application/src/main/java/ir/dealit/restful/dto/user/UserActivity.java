package ir.dealit.restful.dto.user;

import lombok.Builder;
import org.joda.time.DateTime;

@Builder
public record UserActivity(
        String title,
        String description,
        String type,
        DateTime timestamp
) {
}
