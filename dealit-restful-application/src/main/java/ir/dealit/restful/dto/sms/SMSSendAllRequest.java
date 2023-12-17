package ir.dealit.restful.dto.sms;

import java.util.List;

public record SMSSendAllRequest(String from, List<String> to, String text) {
}
