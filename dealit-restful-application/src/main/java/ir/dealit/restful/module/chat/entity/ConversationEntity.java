package ir.dealit.restful.module.chat.entity;

import ir.dealit.restful.module.chat.entity.MessageEntity;
import ir.dealit.restful.module.contract.entity.ContractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "conversations")
public class ConversationEntity {
    private @MongoId ObjectId id;
    private List<MessageEntity> messages;
    private @DocumentReference ContractEntity contract;
}
