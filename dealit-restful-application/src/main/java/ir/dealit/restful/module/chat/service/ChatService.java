package ir.dealit.restful.module.chat.service;

import ir.dealit.restful.module.user.entity.UserEntity;

public interface ChatService {
    int countNewMessage(UserEntity user);
}
