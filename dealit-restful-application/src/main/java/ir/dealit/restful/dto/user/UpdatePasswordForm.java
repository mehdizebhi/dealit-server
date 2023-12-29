package ir.dealit.restful.dto.user;

import jakarta.validation.constraints.Size;

public record UpdatePasswordForm(
        @Size(min = 6, message = "Password must be 6 char") String currentPassword,
        @Size(min = 6, message = "Password must be 6 char") String newPassword,
        @Size(min = 6, message = "Password must be 6 char") String confirmNewPassword
) {
}
