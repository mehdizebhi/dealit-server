package ir.dealit.restful.dto.user;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record PartialUserUpdate(
        @Nullable String username,
        @Nullable String displayName,
        @Nullable @Email String email,
        @Nullable
        @Size(min = 11, max = 11, message = "Phone number must be 11 digits")
        @Pattern(regexp = "^09\\d{9}$", message = "Invalid phone number format") String phoneNumber
) {
}
