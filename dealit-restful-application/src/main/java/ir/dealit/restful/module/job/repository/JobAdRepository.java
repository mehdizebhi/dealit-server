package ir.dealit.restful.module.job.repository;

import ir.dealit.restful.dto.enums.JobAdStatus;
import ir.dealit.restful.module.job.entity.JobAdEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobAdRepository extends MongoRepository<JobAdEntity, ObjectId> {

    @Query(value = "{'status': ?0, 'owner': ?1}", count = true)
    Integer countByStatusAndOwner(JobAdStatus status, ObjectId accountId);

    @Query(value = "{'status': ?0, 'owner': ?1}")
    List<JobAdEntity> findAllByStatusAndOwner(JobAdStatus status, ObjectId accountId);
}
