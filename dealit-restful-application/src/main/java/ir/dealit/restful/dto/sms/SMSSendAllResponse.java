package ir.dealit.restful.dto.sms;

import java.util.List;

public record SMSSendAllResponse(List<String> recIds, String status) {
}
