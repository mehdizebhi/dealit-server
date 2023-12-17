package ir.dealit.restful.dto.sms;


// Type = in, out, all
public record SMSMessagesRequest(String type, String number, int index, int count) {
}
