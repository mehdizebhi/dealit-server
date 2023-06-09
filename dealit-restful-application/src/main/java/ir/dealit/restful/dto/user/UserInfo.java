package ir.dealit.restful.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends RepresentationModel<UserInfo> implements Serializable {

    private ObjectId id;
    private String username;
    private String displayName;
    private String email;
}
