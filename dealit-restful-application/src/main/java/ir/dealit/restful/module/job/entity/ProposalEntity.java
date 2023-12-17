package ir.dealit.restful.module.job.entity;

import ir.dealit.restful.dto.enums.ProposalStatus;
import ir.dealit.restful.dto.contract.MileStone;
import ir.dealit.restful.module.account.entity.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "proposals")
public class ProposalEntity {
    private @MongoId ObjectId id;
    private Double hourlyPrice;
    private List<MileStone> mileStones;
    private String coverLetter;
    private Map<String, String> answers;
    private @DocumentReference(lazy = true) AccountEntity owner;
    private ProposalStatus status;
    private @DocumentReference JobAdEntity jobAd;
    private @CreatedDate Date createdAt;
    private @LastModifiedDate Date updatedAt;
    private boolean seenByClient;
}
