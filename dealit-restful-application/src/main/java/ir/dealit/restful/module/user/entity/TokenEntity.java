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
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tokens")
public class TokenEntity {
    private @MongoId ObjectId id;
    private @Indexed(unique = true) String token;
    private @Indexed(unique = true) String refreshToken;
    private @DocumentReference UserEntity user;
    private @CreatedDate Date createdAt;
    private Date expiredAt;
    private boolean expired;
}
