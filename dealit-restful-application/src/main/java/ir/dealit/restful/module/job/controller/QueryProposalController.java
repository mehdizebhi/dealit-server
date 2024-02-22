package ir.dealit.restful.module.job.controller;

import ir.dealit.restful.api.query.QueryProposalApi;
import ir.dealit.restful.dto.attachment.Attachment;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.proposal.ClientProposalInfo;
import ir.dealit.restful.dto.proposal.Proposal;
import ir.dealit.restful.dto.proposal.FreelancerProposalInfo;
import ir.dealit.restful.module.job.service.ProposalService;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QueryProposalController implements QueryProposalApi {

    private final ProposalService proposalService;

    @Override
    public ResponseEntity<ResponseModel<List<Proposal>>> getAllProposals(Pageable pageable, Authentication authentication) {
        var proposals = proposalService.proposals((UserEntity) authentication.getPrincipal(), pageable);
        return ResponseEntity.ok(
                new ResponseModel.Builder<List<Proposal>>()
                        .data(proposals.toList())
                        .pageMetadata(proposals)
                        .success()
                        .build()
        );
    }

/*    @Override
    public ResponseEntity<ResponseModel<List<Proposal>>> getAllProposalsByType(Pageable pageable, String type, Authentication authentication) {
        return null;
    }*/

    @Override
    public ResponseEntity<ResponseModel<Proposal>> getProposalDetails(ObjectId id, Authentication authentication) {
        var proposal = proposalService.proposal(id, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(
                new ResponseModel.Builder<Proposal>()
                        .data(proposal)
                        .success()
                        .build()
        );
    }

    @Override
    public ResponseEntity<ResponseModel<List<Attachment>>>  getProposalAttachments(ObjectId id, Authentication authentication) {
        return ResponseEntity.ok(new ResponseModel.Builder<List<Attachment>>()
                .data(proposalService.attachmentsOfProposal(id, (UserEntity) authentication.getPrincipal()))
                .success()
                .build());
    }
}
