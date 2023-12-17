package ir.dealit.restful.service.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import ir.dealit.restful.client.SMSClient;
import ir.dealit.restful.dto.enums.SMSMessageType;
import ir.dealit.restful.dto.sms.*;
import ir.dealit.restful.service.SMSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SMSServiceImpl implements SMSService {

    private final SMSClient smsClient;

    @Value("${services.sms.apiKey}")
    private String apiKey;

    @Override
    @Async
    public void send(String from, String to, String text) {
        var body = new SMSSendRequest(to, from, text);
        smsClient.send(body, apiKey);
    }

    @Override
    @Async
    public void sendAll(String from, List<String> to, String text) {
        var body = new SMSSendAllRequest(from, to, text);
        smsClient.sendAll(body, apiKey);
    }

    @Override
    public List<Integer> status(List<Long> recIds) {
        var body = new SMSStatusRequest(recIds);
        return smsClient.receiveStatus(body, apiKey).resultsAsCode();
    }

    @Override
    public List<ObjectNode> messages(SMSMessageType type, String number, int index, int count) {
        var body = new SMSMessagesRequest(type.toString(), number, index, count);
        return smsClient.receiveMessages(body, apiKey).messages();
    }

    @Override
    public long numberOfMessages(boolean isRead) {
        var body = new SMSCountMessagesRequest(isRead);
        return smsClient.countReceivedMessages(body, apiKey).count();
    }

    @Override
    public long credit() {
        return smsClient.getCredit(apiKey).amount();
    }
}
