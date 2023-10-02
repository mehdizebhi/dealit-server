package ir.dealit.restful.dto.job;

import ir.dealit.restful.repository.entity.ExperienceLevel;
import ir.dealit.restful.repository.entity.ProjectLength;
import ir.dealit.restful.repository.entity.WeeklyLoad;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobFilter {
    private @Nullable String search;
    private @Nullable Double minPrice;
    private @Nullable Double maxPrice;
    private @Nullable List<ProjectLength> projectLengths;
    private @Nullable List<WeeklyLoad> weeklyLoads;
    private @Nullable SubmitRange submitRange;
    private @Nullable List<ExperienceLevel> experienceLevels;
    private boolean fixedPrice = false;
    private boolean paymentVerified = false;
    private boolean fromPreviousClients = false;
}
