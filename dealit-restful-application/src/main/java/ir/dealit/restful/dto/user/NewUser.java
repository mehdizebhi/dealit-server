package ir.dealit.restful.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewUser extends RepresentationModel<NewUser> implements Serializable {

    private @NotNull String username;
    private @NotNull String password;
    private @NotNull String confirmPassword;
    private @NotNull @Email String email;
    private @NotNull String displayName;
    private @NotNull String phoneNumber = "";
    private @NotNull String account;
}
