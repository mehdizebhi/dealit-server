package ir.dealit.restful.module.account.entity;

import ir.dealit.restful.module.project.entity.ProjectSpaceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "accounts")
@TypeAlias("ClientAccount")
public class ClientAccountEntity extends AccountEntity{
    private @DocumentReference List<ProjectSpaceEntity> projectSpaces;
    private @DocumentReference ClientProfileEntity profile;
}
