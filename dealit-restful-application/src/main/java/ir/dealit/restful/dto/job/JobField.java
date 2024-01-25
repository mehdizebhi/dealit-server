package ir.dealit.restful.dto.job;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record JobField(@Nullable String id, @NotBlank String title) {
}
