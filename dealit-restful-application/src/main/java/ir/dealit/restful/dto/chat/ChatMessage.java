package ir.dealit.restful.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    private String senderUserId;
    private String chatId;
    private String conversationId;
    private String content;
    private String timestamp;

}
