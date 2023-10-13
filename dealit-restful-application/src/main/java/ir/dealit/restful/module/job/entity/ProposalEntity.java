package ir.dealit.restful.module.job.entity;

import ir.dealit.restful.module.account.entity.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "proposals")
public class ProposalEntity {

    private @MongoId ObjectId id;
    private @DocumentReference(lazy = true) AccountEntity owner;
}