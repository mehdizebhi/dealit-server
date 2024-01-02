package ir.dealit.restful.module.job.repository;

import ir.dealit.restful.module.user.entity.UserEntity;

public interface ProposalRepositoryCustom {

    long countProposalsForClient(UserEntity user);
}
