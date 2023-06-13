package ir.dealit.restful.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewUser {

    private String username;
    private String password;
    private String email;
    private String displayName;
}
