package ir.dealit.restful.dto.job;

import ir.dealit.restful.module.job.entity.ExperienceLevel;
import ir.dealit.restful.module.job.entity.FieldEntity;
import ir.dealit.restful.module.job.entity.ProjectLength;
import ir.dealit.restful.module.job.entity.WeeklyLoad;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobAd extends RepresentationModel<JobAd> {
    private @NotNull String id;
    private @NotNull String title;
    private @NotNull String description;
    private @NotNull boolean fixedPrice;
    private @NotNull Double minBudget;
    private @NotNull Double maxBudget;
    private @NotNull String ownerId;

    private @Nullable ProjectLength projectLength;
    private @Nullable WeeklyLoad weeklyLoad;
    private @Nullable SubmitRange submitRange;
    private @Nullable ExperienceLevel experienceLevel;
    private @Nullable FieldEntity field;
    private @Nullable List<String> tags;
    private @Nullable String createdAt;
    private @Nullable String updatedAt;
}
