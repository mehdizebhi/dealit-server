package ir.dealit.restful.module.wallet.controller;

import ir.dealit.restful.api.query.QueryTransactionApi;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.transaction.Transaction;
import ir.dealit.restful.dto.transaction.TransactionSummary;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.wallet.service.TransactionService;
import ir.dealit.restful.util.DateTimeUtils;
import ir.dealit.restful.util.exception.DealitException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class QueryTransactionController implements QueryTransactionApi {

    private final TransactionService transactionService;


    @Override
    public ResponseEntity<ResponseModel<List<Transaction>>> getAllTransactions(Pageable pageable, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel<Transaction>> getTransaction(ObjectId id, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel<TransactionSummary>> getTransactionSummary(String type, Date startTime, Date endTime, Authentication authentication) {
        return ResponseEntity.ok(new ResponseModel.Builder<TransactionSummary>()
                .data(transactionService.summary(type, new DateTime(startTime), new DateTime(endTime), (UserEntity) authentication.getPrincipal()))
                .success()
                .build());
    }

    @Override
    public ResponseEntity<ResponseModel<Map<String, Object>>> getAnnualReport(String type, Authentication authentication) {
        return null;
    }
}
