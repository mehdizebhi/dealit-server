package ir.dealit.restful.module.job.entity;

import ir.dealit.restful.dto.enums.PositionStatus;
import ir.dealit.restful.module.contract.entity.ContractEntity;
import ir.dealit.restful.module.project.entity.ProjectSpaceEntity;
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

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "job_positions")
public class JobPositionEntity {

    private @MongoId ObjectId id;
    private ObjectId positionId;
    private String title;
    private @DocumentReference UserEntity owner;
    private @DocumentReference ProjectSpaceEntity space;
    private @DocumentReference(lazy = true) List<JobAdEntity> jobAds;
    private @DocumentReference(lazy = true) List<ContractEntity> contracts;
    private @CreatedDate Date createdAt;
    private @LastModifiedDate Date updatedAt;
    private PositionStatus status;
}
