package ir.dealit.restful.module.payment.controller;

import ir.dealit.restful.api.command.CommandPaymentApi;
import ir.dealit.restful.dto.payment.NewPayment;
import ir.dealit.restful.dto.payment.VerifyPayment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public class CommandPaymentController implements CommandPaymentApi {
    @Override
    public ResponseEntity<Void> createPayment(NewPayment newPayment, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<Void> verifyPayment(VerifyPayment verifyPaymentReq, Authentication authentication) {
        return null;
    }
}
