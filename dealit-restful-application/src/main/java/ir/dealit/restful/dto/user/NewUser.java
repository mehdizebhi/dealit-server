package ir.dealit.restful.dto.user;

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

    private String username;
    private String password;
    private String email;
    private String displayName;
}
