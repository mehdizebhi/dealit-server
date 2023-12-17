package ir.dealit.restful.dto.sms;

public record SMSSendRequest(String to, String from, String text) {
}
