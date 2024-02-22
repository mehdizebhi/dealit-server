package ir.dealit.restful.module.job.service;

import ir.dealit.restful.dto.attachment.Attachment;
import ir.dealit.restful.dto.enums.ProposalStatus;
import ir.dealit.restful.dto.proposal.NewProposal;
import ir.dealit.restful.dto.proposal.Proposal;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProposalService {

    Integer countNewProposals(ProposalStatus status, UserEntity user);

    Integer count(ProposalStatus status, UserEntity user);

    ObjectId createNewProposal(NewProposal newProposal, UserEntity user);

    Page<Proposal> proposals(UserEntity owner, Pageable pageable);

    Proposal proposal(ObjectId id, UserEntity owner);

    List<Attachment> attachmentsOfProposal(ObjectId id, UserEntity user);
}
