package ir.dealit.restful.module.chat.service.impl;

import ir.dealit.restful.module.chat.repository.ChatRepository;
import ir.dealit.restful.module.chat.repository.ConversationRepository;
import ir.dealit.restful.module.chat.service.ChatService;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final ConversationRepository conversationRepository;

    @Override
    public int countNewMessage(UserEntity user) {
        return conversationRepository.countNewMessageByChat(user.getChat().getId());
    }
}
