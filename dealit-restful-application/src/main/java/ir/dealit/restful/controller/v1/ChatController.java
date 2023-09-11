package ir.dealit.restful.controller.v1;

import ir.dealit.restful.controller.v1.api.ChatApi;
import ir.dealit.restful.dto.chat.ChatMessage;
import ir.dealit.restful.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController implements ChatApi {

    private final ChatRepository chatRepository;

    @Override
    public ChatMessage sendMessageInConversation(ChatMessage chatMessage,
                                                 String chatId,
                                                 String conversationId,
                                                 Principal user,
                                                 SimpMessageHeaderAccessor headerAccessor) {
        log.info("received message: {}\nchatId: {}\nconversationId: {}\nuser: {}", chatMessage, chatId, conversationId, user);
        return chatMessage;
    }
}
