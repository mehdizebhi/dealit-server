package ir.dealit.restful.dto.auth;

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
public class SignedInUser extends RepresentationModel<SignedInUser> implements Serializable {

    private AuthToken token;
    private String username;
    private String userId;
}
