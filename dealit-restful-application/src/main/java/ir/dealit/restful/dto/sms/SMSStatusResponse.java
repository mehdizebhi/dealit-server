package ir.dealit.restful.dto.sms;

import java.util.List;

public record SMSStatusResponse(List<String> results, List<Integer> resultsAsCode, String status) {
}
