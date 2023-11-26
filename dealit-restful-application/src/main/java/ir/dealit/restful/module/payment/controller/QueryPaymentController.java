package ir.dealit.restful.module.payment.controller;

import ir.dealit.restful.api.query.QueryPaymentApi;
import ir.dealit.restful.dto.enums.PaymentStatus;
import ir.dealit.restful.dto.payment.Payment;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryPaymentController implements QueryPaymentApi {
    @Override
    public ResponseEntity<PagedModel<Payment>> getAllPayments(Pageable pageable, PaymentStatus status, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<EntityModel<Payment>> getPayment(ObjectId id, Authentication authentication) {
        return null;
    }
}
