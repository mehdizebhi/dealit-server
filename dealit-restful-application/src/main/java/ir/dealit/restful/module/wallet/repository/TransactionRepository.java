package ir.dealit.restful.module.wallet.repository;

import ir.dealit.restful.module.wallet.entity.TransactionEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<TransactionEntity, ObjectId> {

    @Query(value = "{'from': ?0}")
    List<TransactionEntity> findAllByWallet(ObjectId walletId);

    @Query(value = "{'to': ?0}")
    List<TransactionEntity> findAllIncomeByWallet(ObjectId walletId);

    @Query(value = "{'from': ?0}")
    List<TransactionEntity> findAllOutcomeByWallet(ObjectId walletId);

    @Query(value = "{'to': ?0, 'seen': false}", count = true)
    Integer countNewTransaction(ObjectId walletId);

    @Query(value = "{'createdAt': {$gte: ?0}, 'createdAt': {$lte: ?1}, to: ?2}")
    List<TransactionEntity> findAllIncomeTransactionByTime(Date startTime, Date endTime, ObjectId walletId);

    @Query(value = "{'createdAt': {$gte: ?0}, 'createdAt': {$lte: ?1}, from: ?2}")
    List<TransactionEntity> findAllOutcomeTransactionByTime(Date startTime, Date endTime, ObjectId walletId);
}
