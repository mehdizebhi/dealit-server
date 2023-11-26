package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.transaction.Transaction;
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

@RequestMapping("/v1/transaction")
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
}
