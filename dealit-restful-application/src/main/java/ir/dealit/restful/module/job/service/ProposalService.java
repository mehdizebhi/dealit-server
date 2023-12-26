package ir.dealit.restful.module.job.service;

import ir.dealit.restful.dto.enums.ProposalStatus;
import ir.dealit.restful.module.user.entity.UserEntity;

public interface ProposalService {

    Integer countNewProposals(ProposalStatus status, UserEntity user);
    Integer count(ProposalStatus status, UserEntity user);
}
