package ir.dealit.restful.module.attachment.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FolderEntity {
    private @DocumentReference List<AttachmentEntity> attachments;
}
