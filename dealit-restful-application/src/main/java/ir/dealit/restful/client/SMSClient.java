package ir.dealit.restful.client;

import ir.dealit.restful.dto.sms.SMSCountMessagesResponse;
import ir.dealit.restful.dto.sms.SMSMessagesRequest;
import ir.dealit.restful.dto.sms.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange(contentType = "application/json")
public interface SMSClient {

    @PostExchange(url = "send/simple/{apiKey}")
    SMSSendResponse send(@RequestBody SMSSendRequest sendRequest, @PathVariable String apiKey);

    @PostExchange(url = "send/advanced/{apiKey}")
    SMSSendAllResponse sendAll(@RequestBody SMSSendAllRequest sendAllRequest, @PathVariable String apiKey);

    @PostExchange(url = "receive/status/{apiKey}")
    SMSStatusResponse receiveStatus(@RequestBody SMSStatusRequest statusRequest, @PathVariable String apiKey);

    @PostExchange(url = "receive/messages/{apiKey}")
    SMSMessagesResponse receiveMessages(@RequestBody SMSMessagesRequest messagesRequest, @PathVariable String apiKey);

    @PostExchange(url = "receive/inboxcount/{apiKey}")
    SMSCountMessagesResponse countReceivedMessages (@RequestBody SMSCountMessagesRequest countMessagesRequest, @PathVariable String apiKey);

    @GetExchange(url = "receive/credit/{apiKey}")
    SMSCreditResponse getCredit(@PathVariable String apiKey);
}
