package ir.dealit.restful.module.wallet.controller;

import ir.dealit.restful.api.query.QueryTransactionApi;
import ir.dealit.restful.dto.transaction.Transaction;
import ir.dealit.restful.dto.transaction.TransactionSummary;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.wallet.service.TransactionService;
import ir.dealit.restful.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
@RequiredArgsConstructor
public class QueryTransactionController implements QueryTransactionApi {

    private final TransactionService transactionService;

    @Override
    public ResponseEntity<PagedModel<Transaction>> getAllTransactions(Pageable pageable, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<EntityModel<Transaction>> getTransaction(ObjectId id, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<EntityModel<TransactionSummary>> getTransactionSummary(Date startTime, Date endTime, Authentication authentication) {
        log.info("start = {}, end = {}", DateTimeUtils.withTimeAtStartOfDay(startTime), DateTimeUtils.withTimeAtEndOfDay(endTime));
        return null;
    }
}
