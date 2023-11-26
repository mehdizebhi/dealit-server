package ir.dealit.restful.api.command;

import ir.dealit.restful.dto.payment.VerifyPayment;
import ir.dealit.restful.dto.payment.NewPayment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/payments")
public interface CommandPaymentApi {

    @PostMapping("/")
    ResponseEntity<Void> createPayment(
            @RequestBody NewPayment newPayment,
            Authentication authentication
    );

    @PostMapping("/verify")
    ResponseEntity<Void> verifyPayment(
            @RequestBody VerifyPayment verifyPaymentReq,
            Authentication authentication
    );
}
