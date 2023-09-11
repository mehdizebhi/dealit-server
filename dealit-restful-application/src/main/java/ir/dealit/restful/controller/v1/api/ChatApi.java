package ir.dealit.restful.controller.v1.api;

import ir.dealit.restful.dto.chat.ChatMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import java.security.Principal;

public interface ChatApi {

    @MessageMapping("/messages/{chatId}/{conversation}")
    @SendTo("/topic/{conversationId}")
    ChatMessage sendMessageInConversation(
            @Payload ChatMessage chatMessage,
            @DestinationVariable("chatId") String chatId,
            @DestinationVariable("conversationId") String conversationId,
            Principal user,
            SimpMessageHeaderAccessor headerAccessor
    );



}
