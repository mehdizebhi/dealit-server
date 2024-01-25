package ir.dealit.restful.dto.job;

import ir.dealit.restful.dto.enums.ExperienceLevel;
import ir.dealit.restful.dto.enums.ProjectLength;
import ir.dealit.restful.dto.enums.WeeklyLoad;
import ir.dealit.restful.module.job.entity.FieldEntity;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
public record NewJobAd (
        @NotBlank String title,
        @NotBlank String description,
        @NotNull boolean fixedPrice,
        @Min(value = 0) Double minBudget,
        @Min(value = 0) Double maxBudget,
        @Nullable ProjectLength projectLength,
        @Nullable WeeklyLoad weeklyLoad,
        @Nullable ExperienceLevel experienceLevel,
        @NotBlank String field,
        @NotBlank String jobPositionId,
        @Size(min = 0, max = 50) int connections,
        @Size(min = 0, max = 5) List<String> questions,
        @Size(min = 0, max = 5) List<String> requirements,
        @Size(min = 0, max = 5) List<String> tags,
        @Size(min = 1, max = 3) List<String> skills,
        @Size(min = 0, max = 5) List<String> files
){
}
