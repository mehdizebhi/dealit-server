package ir.dealit.restful.dto.job;

import ir.dealit.restful.dto.enums.ExperienceLevel;
import ir.dealit.restful.dto.enums.ProjectLength;
import ir.dealit.restful.dto.enums.WeeklyLoad;
import ir.dealit.restful.module.job.entity.FieldEntity;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NewJobAd {
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
}
