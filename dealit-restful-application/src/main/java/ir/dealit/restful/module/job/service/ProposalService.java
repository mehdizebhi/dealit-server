package ir.dealit.restful.module.job.service;

import ir.dealit.restful.dto.proposal.Proposal;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.Optional;

public interface ProposalService {
    //-----------------------------
    // Fetch Data
    //-----------------------------
    Optional<Collection<Proposal>> allProposals(Pageable pageable, Authentication authentication);

    Optional<Proposal> proposal(ObjectId id, Authentication authentication);

    //-----------------------------
    // Manipulate Data
    //-----------------------------
    void createProposal(Proposal newProposal, Authentication authentication);

    void updateProposal(Proposal proposal, Authentication authentication);

    void removeProposal(ObjectId id, Authentication authentication);
}
