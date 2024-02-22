package ir.dealit.restful.module.job.entity;

import ir.dealit.restful.dto.enums.ProjectLength;
import ir.dealit.restful.dto.enums.ProposalStatus;
import ir.dealit.restful.module.attachment.entity.AttachmentEntity;
import ir.dealit.restful.module.contract.entity.MilestoneEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
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

import java.math.BigDecimal;
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
    private BigDecimal hourlyPriceRate;
    private BigDecimal fixedPriceRate;
    private List<MilestoneEntity> milestones;
    private ProjectLength suggestProjectLength;
    private String coverLetter;
    private Map<String, String> answers;
    private @DocumentReference UserEntity owner;
    private ProposalStatus status;
    private @DocumentReference JobAdEntity jobAd;
    private @CreatedDate Date createdAt;
    private @LastModifiedDate Date updatedAt;
    private boolean seenByClient;
    private @DocumentReference(lazy = true) List<AttachmentEntity> attachment;
}
