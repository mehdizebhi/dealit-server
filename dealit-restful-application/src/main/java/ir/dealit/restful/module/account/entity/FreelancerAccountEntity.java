package ir.dealit.restful.module.account.entity;

import ir.dealit.restful.module.job.entity.JobSpaceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "accounts")
@TypeAlias("FreelancerAccount")
public class FreelancerAccountEntity extends AccountEntity {
    private @DocumentReference(lazy = true) JobSpaceEntity jobSpace;
}
