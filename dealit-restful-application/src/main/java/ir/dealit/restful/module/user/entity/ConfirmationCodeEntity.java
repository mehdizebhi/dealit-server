package ir.dealit.restful.module.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "confirmation-codes")
public class ConfirmationCodeEntity {
    private @MongoId ObjectId id;
    private @Indexed(unique = true) String code;
    private @CreatedDate Date createdAt;
    private Date expireAt;
    private @DocumentReference UserEntity user;
    private String reason;
    private boolean used;
}
