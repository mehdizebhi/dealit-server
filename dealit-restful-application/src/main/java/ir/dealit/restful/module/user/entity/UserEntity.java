package ir.dealit.restful.module.user.entity;

import ir.dealit.restful.module.account.entity.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class UserEntity implements UserDetails {

    private @MongoId ObjectId id;
    private @Indexed(unique = true) String username;
    private String password;
    private String displayName;
    private String email;
    private String phoneNumber;
    private @CreatedDate LocalDateTime createdAt;
    private @LastModifiedDate LocalDateTime updatedAt;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

//    @ReadOnlyProperty
//    @DocumentReference(lookup = "{'user':?#{#self._id}}")

    private @DocumentReference(lazy = true) List<AccountEntity> accounts;
    private @DocumentReference Set<RoleEntity> roles;
    private @Transient Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void addAccount(AccountEntity accountEntity) {
        if (this.accounts == null) {
            this.accounts = new ArrayList<>();
        }
        this.accounts.add(accountEntity);
    }

    public void addRoles(RoleEntity... roles) {
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }
        this.roles.addAll(Set.of(roles));
    }
}
