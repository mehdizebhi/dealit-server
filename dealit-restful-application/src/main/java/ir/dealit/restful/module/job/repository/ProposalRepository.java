package ir.dealit.restful.module.job.repository;

import ir.dealit.restful.dto.enums.ProposalStatus;
import ir.dealit.restful.module.job.entity.ProposalEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProposalRepository extends MongoRepository<ProposalEntity, ObjectId> {

    @Query(value = "{'jobAd':  ?0, 'seenByClient': ?1}", count = true)
    Integer countByJobAdAndSeenByClient(ObjectId jobAdId, boolean seenByClient);

    @Query(value = "{'status': ?0, 'owner': ?1}", count = true)
    Integer countByStatusAndOwner(ProposalStatus status, ObjectId userId);

    @Query(value = "{'owner': ?0}", count = true)
    Integer countByOwner(ObjectId userId);

    @Query(value = "{'owner': ?0, 'jobAd':  ?1}", count = true)
    Integer countByOwnerIdAndJobAdId(ObjectId ownerId, ObjectId jobAdId);

    Page<ProposalEntity> findByOwner(UserEntity owner, Pageable pageable);

    Optional<ProposalEntity> findByIdAndOwner(ObjectId id, UserEntity owner);
}
