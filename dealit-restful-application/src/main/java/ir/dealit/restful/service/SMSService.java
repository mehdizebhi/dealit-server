package ir.dealit.restful.service;


import com.fasterxml.jackson.databind.node.ObjectNode;
import ir.dealit.restful.dto.enums.SMSMessageType;

import java.util.List;

public interface SMSService {

    void send(String from, String to, String text);
    void sendAll(String from, List<String> to, String text);
    List<Integer> status(List<Long> recIds);
    List<ObjectNode> messages(SMSMessageType type, String number, int index, int count);
    long numberOfMessages (boolean isRead);
    long credit();
}
