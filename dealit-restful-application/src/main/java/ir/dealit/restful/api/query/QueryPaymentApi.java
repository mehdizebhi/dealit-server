package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.enums.PaymentStatus;
import ir.dealit.restful.dto.payment.Payment;
import ir.dealit.restful.dto.wallet.PaymentHistory;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@RequestMapping("/v1/payments")
public interface QueryPaymentApi {

    @GetMapping("/")
    ResponseEntity<PagedModel<Payment>> getAllPayments(
            @PageableDefault Pageable pageable,
            @RequestParam(value = "status", required = false) PaymentStatus status,
            Authentication authentication
    );

    @GetMapping("/{id}")
    ResponseEntity<EntityModel<Payment>> getPayment(
            @PathVariable("id") ObjectId id,
            Authentication authentication
    );
}
