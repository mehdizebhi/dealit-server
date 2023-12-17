package ir.dealit.restful.module.job.repository;

import ir.dealit.restful.dto.enums.ProposalStatus;
import ir.dealit.restful.module.job.entity.ProposalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends MongoRepository<ProposalEntity, ObjectId> {

    @Query(value = "{'jobAd':  ?0, 'seenByClient': ?1}", count = true)
    Integer countByJobAdAndSeenByClient(ObjectId jobAdId, boolean seenByClient);

    @Query(value = "{'status': ?0, 'owner': ?1}", count = true)
    Integer countByStatusAndOwner(ProposalStatus status, ObjectId ownerId);

}
