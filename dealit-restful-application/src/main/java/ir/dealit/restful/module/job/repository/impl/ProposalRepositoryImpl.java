package ir.dealit.restful.module.job.repository.impl;

import ir.dealit.restful.module.job.repository.ProposalRepositoryCustom;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProposalRepositoryImpl implements ProposalRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public long countProposalsForClient(UserEntity user) {
        return 0;
    }
}
