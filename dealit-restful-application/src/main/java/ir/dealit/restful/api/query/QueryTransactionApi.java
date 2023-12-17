package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.transaction.Transaction;
import ir.dealit.restful.dto.transaction.TransactionSummary;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@RequestMapping("/v1/transactions")
public interface QueryTransactionApi {

    @GetMapping("/")
    ResponseEntity<PagedModel<Transaction>> getAllTransactions(
            @PageableDefault Pageable pageable,
            Authentication authentication
    );

    @GetMapping("/{id}")
    ResponseEntity<EntityModel<Transaction>> getTransaction(
            @PathVariable("id") ObjectId id,
            Authentication authentication
    );

    @GetMapping("/summary")
    ResponseEntity<EntityModel<TransactionSummary>> getTransactionSummary(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startTime,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endTime,
            Authentication authentication
    );
}
