package ir.dealit.restful.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePasswordForm(
        @NotBlank @Size(min = 6, message = "Password must be 6 char") String currentPassword,
        @NotBlank @Size(min = 6, message = "Password must be 6 char") String newPassword,
        @NotBlank @Size(min = 6, message = "Password must be 6 char") String confirmNewPassword
) {
}
