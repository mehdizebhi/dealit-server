package ir.dealit.restful.module.contract.repository;

import ir.dealit.restful.dto.enums.ContractStatus;
import ir.dealit.restful.module.contract.entity.ContractEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends MongoRepository<ContractEntity, ObjectId> {

    @Query(value = "{'status': ?0, 'hired': ?1}", count = true)
    Integer countByStatusAndHired(ContractStatus status, ObjectId userId);

    @Query(value = "{'hired': ?0}", count = true)
    Integer countByHired(ObjectId userId);

    @Query(value = "{'status': ?0, 'hiredBy': ?1}", count = true)
    Integer countByStatusAndHiredBy(ContractStatus status, ObjectId userId);

    @Query(value = "{'hiredBy': ?0}", count = true)
    Integer countByHiredBy(ObjectId userId);

}
