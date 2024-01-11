package ir.dealit.restful.dto.timetracker;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;

@Builder
public record RecordTime(
        @Nullable ObjectId id,
        @NotBlank String title,
        @NotBlank String description,
        @NotNull DateTime start,
        @NotNull DateTime end,
        @NotNull ObjectId contractId,
        @Nullable String contractTitle
) {
}
