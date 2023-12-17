package ir.dealit.restful.module.chat.entity;

import ir.dealit.restful.module.attachment.entity.AttachmentEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageChatEntity {
    private @DocumentReference UserEntity from;
    private @DocumentReference UserEntity to;
    private String text;
    private @DocumentReference AttachmentEntity attachment;
    private Date sendAt;
    private boolean seen;
}
