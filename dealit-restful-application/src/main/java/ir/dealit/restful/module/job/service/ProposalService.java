package ir.dealit.restful.module.job.service;

import ir.dealit.restful.dto.enums.ProposalStatus;
import ir.dealit.restful.dto.proposal.Proposal;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface ProposalService {
    //-----------------------------
    // Fetch Data
    //-----------------------------
    Page<Proposal> allProposals(Pageable pageable, UserEntity user);

    Proposal proposal(ObjectId id, UserEntity user);

    Integer countNewProposals(ProposalStatus status, UserEntity user);

    Integer count(ProposalStatus status, UserEntity user);

    //-----------------------------
    // Manipulate Data
    //-----------------------------
    Optional<ObjectId> createProposal(Proposal newProposal, UserEntity user);

    void updateProposal(Proposal proposal, UserEntity user);

    void removeProposal(ObjectId id, UserEntity user);
}
