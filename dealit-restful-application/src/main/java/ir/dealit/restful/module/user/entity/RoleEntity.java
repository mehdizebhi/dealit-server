package ir.dealit.restful.module.user.entity;

import ir.dealit.restful.dto.enums.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("roles")
public class RoleEntity {
    private @MongoId ObjectId id;
    private @Indexed(unique = true) String name;
    private Set<String> privileges;

    public void addPrivileges(String... privileges) {
        if (this.privileges == null) {
            this.privileges = new HashSet<>();
        }
        this.privileges.addAll(Set.of(privileges));
    }
}
