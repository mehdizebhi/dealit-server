package ir.dealit.restful.module.job.service.impl;

import ir.dealit.restful.dto.proposal.Proposal;
import ir.dealit.restful.module.job.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProposalServiceImpl implements ProposalService {

    @Override
    public Optional<Collection<Proposal>> allProposals(Pageable pageable, Authentication authentication) {
        return Optional.empty();
    }

    @Override
    public Optional<Proposal> proposal(ObjectId id, Authentication authentication) {
        return Optional.empty();
    }

    @Override
    public void createProposal(Proposal newProposal, Authentication authentication) {

    }

    @Override
    public void updateProposal(Proposal proposal, Authentication authentication) {

    }

    @Override
    public void removeProposal(ObjectId id, Authentication authentication) {

    }
}
