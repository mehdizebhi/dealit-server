package ir.dealit.restful.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageEntity {

    private AccountEntity sender;
    private ConversationEntity receiver;
    private String text;
    private LocalDateTime timestamp;

}
