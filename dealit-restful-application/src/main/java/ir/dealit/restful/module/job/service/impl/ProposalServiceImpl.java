package ir.dealit.restful.module.job.service.impl;

import ir.dealit.restful.dto.enums.JobAdStatus;
import ir.dealit.restful.dto.enums.ProposalStatus;
import ir.dealit.restful.dto.proposal.Proposal;
import ir.dealit.restful.module.job.repository.ProposalRepository;
import ir.dealit.restful.module.job.service.JobAdService;
import ir.dealit.restful.module.job.service.ProposalService;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProposalServiceImpl implements ProposalService {

    private final ProposalRepository proposalRepository;
    private final JobAdService jobAdService;
    private final UserAuthService userAuthService;

    @Override
    public Integer countNewProposals(ProposalStatus status, UserEntity user) {
        var jobAds = jobAdService.allJobAd(JobAdStatus.ACTIVE, user);
        Integer count = 0;
        for (var jobAd : jobAds) {
            count += proposalRepository.countByJobAdAndSeenByClient(jobAd.getId(), false);
        }
        return count;
    }

    @Override
    public Integer count(ProposalStatus status, UserEntity owner) {
        return proposalRepository.countByStatusAndOwner(status, owner.getId());
    }
}
