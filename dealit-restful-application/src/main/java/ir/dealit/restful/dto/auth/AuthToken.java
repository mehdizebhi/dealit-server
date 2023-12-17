package ir.dealit.restful.dto.auth;

import jakarta.annotation.Nullable;
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
public class AuthToken extends RepresentationModel<AuthToken> implements Serializable {

    private @NotNull String accessToken;
    private @Nullable String refreshToken;
    private @NotNull String type;
    private @Nullable long exp;
}
