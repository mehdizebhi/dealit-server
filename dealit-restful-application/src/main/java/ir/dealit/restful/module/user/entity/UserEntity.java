package ir.dealit.restful.module.user.entity;

import ir.dealit.restful.dto.enums.AccountType;
import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.module.account.entity.ClientAccountEntity;
import ir.dealit.restful.module.account.entity.FreelancerAccountEntity;
import ir.dealit.restful.module.chat.entity.ChatEntity;
import ir.dealit.restful.module.inbox.entity.InboxEntity;
import ir.dealit.restful.module.wallet.entity.WalletEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class UserEntity implements UserDetails {

    private @MongoId ObjectId id;
    private @Indexed(unique = true) String username;
    private @Size(min = 6, message = "Password must be 6 char") String password;
    private String displayName;
    private @Email String email;
    private @Size(min = 11, max = 11, message = "Phone number must be 11 digits")
            @Pattern(regexp = "^09\\d{9}$", message = "Invalid phone number format") String phoneNumber;
    private @CreatedDate Date createdAt;
    private @LastModifiedDate Date updatedAt;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private boolean phoneConfirmed;
    private boolean emailConfirmed;
    private int connections;
    private String pictureHref;
    private @DocumentReference WalletEntity wallet;
    private @DocumentReference InboxEntity inbox;
    private @DocumentReference(lazy = true) ChatEntity chat;
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

    public List<AccountType> getAccountTypes() {
        List<AccountType> types = new ArrayList<>();
        for (var accountEntity : accounts) {
            if (accountEntity instanceof ClientAccountEntity) {
                types.add(AccountType.CLIENT);
            } else if (accountEntity instanceof FreelancerAccountEntity) {
                types.add(AccountType.FREELANCER);
            }
        }
        return types;
    }
}
