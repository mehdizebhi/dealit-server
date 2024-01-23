package ir.dealit.restful.config.db;

import ir.dealit.restful.dto.user.NewUser;
import ir.dealit.restful.module.chat.entity.ChatEntity;
import ir.dealit.restful.module.chat.entity.ConversationEntity;
import ir.dealit.restful.module.contract.entity.ContractEntity;
import ir.dealit.restful.module.job.entity.JobAdEntity;
import ir.dealit.restful.module.job.entity.JobPositionEntity;
import ir.dealit.restful.module.job.entity.ProposalEntity;
import ir.dealit.restful.module.timetracker.entity.WorkTimeEntity;
import ir.dealit.restful.module.user.entity.RoleEntity;
import ir.dealit.restful.module.user.entity.TokenEntity;
import ir.dealit.restful.module.user.service.RoleDaoService;
import ir.dealit.restful.module.user.service.UserDaoService;
import ir.dealit.restful.module.wallet.entity.TransactionEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class MongoMigration {

    private final RoleDaoService roleDaoService;
    private final MongoTemplate mongoTemplate;
    private final UserDaoService userDaoService;

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Value("${app.admin.phoneNumber}")
    private String adminPhoneNumber;

    @Value("${app.admin.email}")
    private String adminEmail;


    public void init() {
        log.info("Starting to execute migration mongodb to populate some collections.");
        createPrimitiveCollections();
        createPrimitiveRoles();
        createAdmin();
        log.info("Ending execute migration mongodb to populate some collections.");
    }

    private void createPrimitiveCollections() {
        if (!mongoTemplate.collectionExists(ProposalEntity.class)) mongoTemplate.createCollection(ProposalEntity.class);
        if (!mongoTemplate.collectionExists(JobAdEntity.class)) mongoTemplate.createCollection(JobAdEntity.class);
        if (!mongoTemplate.collectionExists(ContractEntity.class)) mongoTemplate.createCollection(ContractEntity.class);
        if (!mongoTemplate.collectionExists(TransactionEntity.class)) mongoTemplate.createCollection(TransactionEntity.class);
        if (!mongoTemplate.collectionExists(ChatEntity.class)) mongoTemplate.createCollection(ChatEntity.class);
        if (!mongoTemplate.collectionExists(ConversationEntity.class)) mongoTemplate.createCollection(ConversationEntity.class);
        if (!mongoTemplate.collectionExists(TokenEntity.class)) mongoTemplate.createCollection(TokenEntity.class);
        if (!mongoTemplate.collectionExists(JobPositionEntity.class)) mongoTemplate.createCollection(JobPositionEntity.class);
        if (!mongoTemplate.collectionExists(WorkTimeEntity.class)) mongoTemplate.createCollection(WorkTimeEntity.class);
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

    private void createAdmin() {
        try {
            userDaoService.registerAdmin(NewUser.builder()
                    .username(adminUsername)
                    .password(adminPassword)
                    .confirmPassword(adminPassword)
                    .phoneNumber(adminPhoneNumber)
                    .email(adminEmail)
                    .displayName(adminUsername)
                    .account("admin")
                    .build());
        } catch (Exception e) {
            log.warn("Admin already exist.");
        }
    }
}
