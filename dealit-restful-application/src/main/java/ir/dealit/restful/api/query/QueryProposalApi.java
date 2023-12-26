package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.proposal.ClientProposalInfo;
import ir.dealit.restful.dto.proposal.Proposal;
import ir.dealit.restful.dto.proposal.FreelancerProposalInfo;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/proposals")
public interface QueryProposalApi {

    @GetMapping("/")
    ResponseEntity<PagedModel<Proposal>> getAllProposals(
            @PageableDefault Pageable pageable,
            Authentication authentication
    );

    @GetMapping("/{type}")
    ResponseEntity<PagedModel<Proposal>> getAllProposalsByType(
            @PageableDefault Pageable pageable,
            @PathVariable("type") String type,
            Authentication authentication
    );

    @GetMapping("/{id}")
    ResponseEntity<EntityModel<Proposal>> getProposal(
            @PathVariable("id") ObjectId id,
            Authentication authentication
    );

    @GetMapping("/freelancer/info")
    ResponseEntity<EntityModel<FreelancerProposalInfo>> getFreelancerProposalInfo(
            Authentication authentication
    );

    @GetMapping("/client/info")
    ResponseEntity<EntityModel<ClientProposalInfo>> getClientProposalInfo(
            Authentication authentication
    );
}
