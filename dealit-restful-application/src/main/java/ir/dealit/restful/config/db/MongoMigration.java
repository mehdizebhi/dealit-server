package ir.dealit.restful.config.db;

import ir.dealit.restful.module.user.entity.RoleEntity;
import ir.dealit.restful.module.user.service.RoleDaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class MongoMigration {

    private final RoleDaoService roleDaoService;

    public void init() {
        log.info("Starting to execute migration mongodb to populate some collections.");
        createPrimitiveRoles();
        log.info("Ending execute migration mongodb to populate some collections.");
    }

    private void createPrimitiveRoles() {
        var roles = getPrimitiveRoles();
        for (var role : roles) {
            try {
                roleDaoService.registerRole(role);
            } catch (Exception e) {
                log.warn("Something happened when execute migration mongodb. Maybe the data is exist already.");
            }
        }
    }

    private Collection<RoleEntity> getPrimitiveRoles() {
        final RoleEntity ROLE_ADMIN = RoleEntity.builder()
                .name("ROLE_ADMIN")
                .privileges(Set.of("permission:read", "permission:write"))
                .build();
        final RoleEntity ROLE_USER = RoleEntity.builder()
                .name("ROLE_USER")
                .privileges(Set.of("permission:read", "permission:write"))
                .build();
        final RoleEntity ROLE_CLIENT = RoleEntity.builder()
                .name("ROLE_CLIENT")
                .privileges(Set.of("permission:read", "permission:write"))
                .build();
        final RoleEntity ROLE_FREELANCER = RoleEntity.builder()
                .name("ROLE_FREELANCER")
                .privileges(Set.of("permission:read", "permission:write"))
                .build();
        final RoleEntity ROLE_SUBSCRIBER = RoleEntity.builder()
                .name("ROLE_SUBSCRIBER")
                .privileges(Set.of("permission:read", "permission:write"))
                .build();
        return Set.of(
                ROLE_ADMIN,
                ROLE_USER,
                ROLE_CLIENT,
                ROLE_FREELANCER,
                ROLE_SUBSCRIBER);
    }
}
