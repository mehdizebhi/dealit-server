package ir.dealit.restful.module.timetracker.entity;

import ir.dealit.restful.module.contract.entity.ContractEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "work_times")
public class WorkTimeEntity {

    private @MongoId ObjectId id;
    private String title;
    private String description;
    private Date start;
    private Date end;
    private @DocumentReference ContractEntity contract;
    private @DocumentReference UserEntity owner;
}
