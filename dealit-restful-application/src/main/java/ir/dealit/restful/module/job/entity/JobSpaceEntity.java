package ir.dealit.restful.module.job.entity;

import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.module.contract.entity.ContractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "job_spaces")
public class JobSpaceEntity {
    private @MongoId ObjectId id;
    private @DocumentReference(lazy = true) List<ProposalEntity> proposals;
    private @DocumentReference(lazy = true) List<InvitationEntity> invites;
    private @DocumentReference(lazy = true) List<ContractEntity> contracts;
    private @DocumentReference AccountEntity owner;

    public JobSpaceEntity(AccountEntity owner) {
        this.owner = owner;
        this.proposals = Collections.emptyList();
        this.invites = Collections.emptyList();
        this.contracts = Collections.emptyList();
    }
}
