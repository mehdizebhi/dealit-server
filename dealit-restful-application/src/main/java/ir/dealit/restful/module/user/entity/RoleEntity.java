package ir.dealit.restful.module.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "roles")
public class RoleEntity implements GrantedAuthority {
    private @MongoId ObjectId id;
    private String athority;

    @Override
    public String getAuthority() {
        return this.athority;
    }
}
