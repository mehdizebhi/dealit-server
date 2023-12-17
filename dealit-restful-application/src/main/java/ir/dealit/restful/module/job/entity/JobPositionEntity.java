package ir.dealit.restful.module.job.entity;

import ir.dealit.restful.module.contract.entity.ContractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPositionEntity {
    private ObjectId positionId;
    private String title;
    private @DocumentReference(lazy = true) List<JobAdEntity> jobAds;
    private @DocumentReference(lazy = true) List<ContractEntity> contracts;
    private @CreatedDate Date createdAt;
    private @LastModifiedDate Date updatedAt;
}
