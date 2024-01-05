package ir.dealit.restful.module.job.service.impl;

import ir.dealit.restful.dto.enums.AccountType;
import ir.dealit.restful.dto.enums.JobAdStatus;
import ir.dealit.restful.dto.enums.ProposalStatus;
import ir.dealit.restful.module.job.repository.JobAdRepository;
import ir.dealit.restful.module.job.repository.ProposalRepository;
import ir.dealit.restful.module.job.service.JobAdService;
import ir.dealit.restful.module.job.service.ProposalService;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProposalServiceImpl implements ProposalService {

    private final ProposalRepository proposalRepository;
    private final JobAdService jobAdService;
    private final UserAuthService userAuthService;
    private final JobAdRepository jobAdRepository;

    @Override
    public Integer countNewProposals(ProposalStatus status, UserEntity user) {
        /*var jobAds = jobAdService.jobAds(JobAdStatus.ACTIVE, user);
        Integer count = 0;
        for (var jobAd : jobAds) {
            count += proposalRepository.countByJobAdAndSeenByClient(jobAd.getId(), false);
        }
        return count;*/
        return 0;
    }

    @Override
    public Integer count(ProposalStatus status, UserEntity owner) {
        return proposalRepository.countByStatusAndOwner(status, owner.getId());
    }
}
