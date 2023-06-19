package ir.dealit.restful.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends RepresentationModel<User> implements Serializable {

    private ObjectId id;
    private String username;
    private String password;
    private String displayName;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
}
