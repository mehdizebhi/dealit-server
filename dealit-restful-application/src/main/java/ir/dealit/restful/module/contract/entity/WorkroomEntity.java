package ir.dealit.restful.module.contract.entity;

import ir.dealit.restful.module.attachment.entity.FolderEntity;
import ir.dealit.restful.module.timetracker.entity.WorkTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("workrooms")
public class WorkroomEntity {
    private @MongoId ObjectId id;
    private @DocumentReference ContractEntity contract;
    private FolderEntity folderEntity;
    private List<WorkTimeEntity> workTimes;

}
