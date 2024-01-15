package ir.dealit.restful.module.contract.entity;

import ir.dealit.restful.dto.enums.ContractStatus;
import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.dto.enums.Currency;
import ir.dealit.restful.module.job.entity.JobPositionEntity;
import ir.dealit.restful.module.timetracker.entity.WorkTimeEntity;
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
@Document(collection = "contracts")
public class ContractEntity {
    private @MongoId ObjectId id;
    private String title;
    private @DocumentReference WorkroomEntity workroom;
    private @DocumentReference UserEntity hired;
    private @DocumentReference UserEntity hiredBy;
    private @DocumentReference JobPositionEntity jobPosition;
    private @DocumentReference List<MilestoneEntity> milestone;
    private @DocumentReference List<WorkTimeEntity> workTimes;
    private boolean fixedPrice;
    private Double budget;
    private Currency currency;
    private Date start;
    private Date end;
    private @CreatedDate Date createdAt;
    private @LastModifiedDate Date updatedAt;
    private ContractStatus status;
}
