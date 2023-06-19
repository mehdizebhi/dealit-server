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
public class UserSignUpReq extends RepresentationModel<UserSignUpReq> implements Serializable {

    private String username;
    private String password;
    private String email;
    private String displayName;
}
