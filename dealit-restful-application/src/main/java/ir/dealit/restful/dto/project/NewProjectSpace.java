package ir.dealit.restful.dto.project;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
public record NewProjectSpace(@NotBlank String title) {
}
