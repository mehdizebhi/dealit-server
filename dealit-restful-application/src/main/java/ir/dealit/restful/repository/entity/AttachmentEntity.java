package ir.dealit.restful.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "attachments")
public class AttachmentEntity {
    private @MongoId ObjectId id;
    private @Indexed(unique = true) String fileId;
    private String fileName;
    private String fileType;
    private String fileExtension;
    private long fileSize;
    private String uri;
}
