package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.attachment.Attachment;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.proposal.ClientProposalInfo;
import ir.dealit.restful.dto.proposal.Proposal;
import ir.dealit.restful.dto.proposal.FreelancerProposalInfo;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/v1/proposals")
public interface QueryProposalApi {

    @GetMapping("")
    ResponseEntity<ResponseModel<List<Proposal>>> getAllProposals(
            @PageableDefault(sort = {"createdAt"}, direction = Sort.Direction.DESC) Pageable pageable,
            Authentication authentication
    );

/*    @GetMapping("")
    ResponseEntity<ResponseModel<List<Proposal>>> getAllProposalsByType(
            @PageableDefault Pageable pageable,
            @RequestParam("type") String type,
            Authentication authentication
    );*/

    @GetMapping("/{id}")
    ResponseEntity<ResponseModel<Proposal>> getProposalDetails(
            @PathVariable("id") ObjectId id,
            Authentication authentication
    );

    @GetMapping("/{id}/attachments")
    ResponseEntity<ResponseModel<List<Attachment>>>  getProposalAttachments(
            @PathVariable("id") ObjectId id,
            Authentication authentication
    );

/*    @GetMapping("/freelancer/info")
    ResponseEntity<EntityModel<FreelancerProposalInfo>> getFreelancerProposalInfo(
            Authentication authentication
    );

    @GetMapping("/client/info")
    ResponseEntity<EntityModel<ClientProposalInfo>> getClientProposalInfo(
            Authentication authentication
    );*/
}
