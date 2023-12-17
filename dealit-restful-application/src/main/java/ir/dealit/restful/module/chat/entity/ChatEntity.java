package ir.dealit.restful.module.chat.entity;

import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "chats")
public class ChatEntity {

    private @MongoId ObjectId id;
    private @DocumentReference @Indexed(unique = true) UserEntity owner;
    private @DocumentReference List<ConversationEntity> conversations;

    public ChatEntity(UserEntity owner) {
        this.owner = owner;
        this.conversations = Collections.emptyList();
    }
}
