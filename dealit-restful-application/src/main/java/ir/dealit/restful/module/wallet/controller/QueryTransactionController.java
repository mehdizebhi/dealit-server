package ir.dealit.restful.module.wallet.controller;

import ir.dealit.restful.api.query.QueryTransactionApi;
import ir.dealit.restful.dto.transaction.Transaction;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QueryTransactionController implements QueryTransactionApi {
    @Override
    public ResponseEntity<PagedModel<Transaction>> getAllTransactions(Pageable pageable, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<EntityModel<Transaction>> getTransaction(ObjectId id, Authentication authentication) {
        return null;
    }
}
