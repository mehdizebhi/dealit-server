package ir.dealit.restful.module.contract.entity;

import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.dto.enums.Currency;
import ir.dealit.restful.module.job.entity.JobPositionEntity;
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

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "contracts")
public class ContractEntity {
    private @MongoId ObjectId id;
    private String title;
    private @DocumentReference WorkroomEntity workroom;
    private @DocumentReference AccountEntity hired;
    private @DocumentReference AccountEntity hiredBy;
    private ObjectId jobPositionId;
    private boolean fixedPrice;
    private Double budget;
    private Currency currency;
    private LocalDateTime start;
    private LocalDateTime end;
    private @CreatedDate LocalDateTime createdAt;
    private @LastModifiedDate LocalDateTime updatedAt;
}
