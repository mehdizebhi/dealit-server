package ir.dealit.restful.module.timetracker.repository;

import ir.dealit.restful.module.contract.entity.ContractEntity;
import ir.dealit.restful.module.timetracker.entity.WorkTimeEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkTimeRepository extends MongoRepository<WorkTimeEntity, ObjectId> {

    Optional<WorkTimeEntity> findByIdAndOwner(ObjectId id, UserEntity owner);

    @Query(value = "{'contract': ?0}")
    Page<WorkTimeEntity> findByContract(ObjectId contractId, Pageable pageable);

    Page<WorkTimeEntity> findByOwner(UserEntity owner, Pageable pageable);
}
