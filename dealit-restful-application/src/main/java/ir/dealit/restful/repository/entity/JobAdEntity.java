package ir.dealit.restful.repository.entity;

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

import java.time.LocalDateTime;
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
    private Double minPrice;
    private Double maxPrice;
    private ProjectLength projectLength;
    private WeeklyLoad weeklyLoad;
    private @DocumentReference List<AttachmentEntity> attachment;
    private @CreatedDate LocalDateTime createdAt;
    private @LastModifiedDate LocalDateTime updatedAt;
    private @DocumentReference(lazy = true) AccountEntity owner;

    private @TextScore Float score;
}
