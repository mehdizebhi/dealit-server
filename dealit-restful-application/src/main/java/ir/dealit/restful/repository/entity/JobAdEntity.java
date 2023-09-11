package ir.dealit.restful.repository.entity;

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
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "job_ads")
public class JobAdEntity {

    private @MongoId ObjectId id;
    private String title;
    private String description;
    private boolean isFixedPrice;
    private Double minPrice;
    private Double maxPrice;
    private ExperienceLevel experienceLevel;
    private FieldEntity field;
    private int projectLength;
    private int weeklyLoad;
    private @DocumentReference List<AttachmentEntity> attachment;
    private @CreatedDate LocalDateTime createdAt;
    private @LastModifiedDate LocalDateTime updatedAt;

    @DocumentReference(lazy = true)
    private AccountEntity owner;
}
