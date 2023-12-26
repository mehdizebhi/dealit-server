package ir.dealit.restful.module.project.entity;

import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.module.job.entity.JobPositionEntity;
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

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "project_spaces")
public class ProjectSpaceEntity {
    private @MongoId ObjectId id;
    private String title;
    private @DocumentReference UserEntity owner;
    private @DocumentReference List<JobPositionEntity> jobPositions;
    private @CreatedDate Date createdAt;
    private @LastModifiedDate Date updatedAt;

    public ProjectSpaceEntity(UserEntity owner, String title) {
        this.owner = owner;
        this.title = title;
        this.jobPositions = Collections.emptyList();
    }
}
