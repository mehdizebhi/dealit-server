package ir.dealit.restful.module.job.entity;

import ir.dealit.restful.dto.enums.ExperienceLevel;
import ir.dealit.restful.dto.enums.JobAdStatus;
import ir.dealit.restful.dto.enums.ProjectLength;
import ir.dealit.restful.dto.enums.WeeklyLoad;
import ir.dealit.restful.module.attachment.entity.AttachmentEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.mongodb.core.mapping.TextScore;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "job_ads")
public class JobAdEntity {

    private @MongoId ObjectId id;
    private @TextIndexed(weight = 3) String title;
    private @TextIndexed(weight = 2) String description;
    private FieldEntity field;
    private List<String> tags;
    private ExperienceLevel experienceLevel;
    private boolean fixedPrice;
    private Double minBudget;
    private Double maxBudget;
    private ProjectLength projectLength;
    private WeeklyLoad weeklyLoad;
    private @DocumentReference(lazy = true) List<AttachmentEntity> attachment;
    private @CreatedDate Date createdAt;
    private @LastModifiedDate Date updatedAt;
    private @DocumentReference UserEntity owner;
    private List<String> questions;
    private List<String> requirements;
    private @DocumentReference JobPositionEntity jobPosition;
    private JobAdStatus status;
    private @DocumentReference List<SkillEntity> skills;
    private @TextScore Float score;
    private int requiredConnection;
}
