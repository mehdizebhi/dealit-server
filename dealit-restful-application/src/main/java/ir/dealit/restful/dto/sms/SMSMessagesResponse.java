package ir.dealit.restful.dto.sms;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public record SMSMessagesResponse(List<ObjectNode> messages, String status) {
}
