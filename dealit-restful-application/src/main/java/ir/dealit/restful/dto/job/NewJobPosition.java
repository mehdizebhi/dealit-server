package ir.dealit.restful.dto.job;

import jakarta.validation.constraints.NotBlank;

public record NewJobPosition(
        @NotBlank String title
) {
}
