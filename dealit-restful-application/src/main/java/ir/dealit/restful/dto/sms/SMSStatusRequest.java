package ir.dealit.restful.dto.sms;

import java.util.List;

public record SMSStatusRequest(List<Long> recIds) {
}
