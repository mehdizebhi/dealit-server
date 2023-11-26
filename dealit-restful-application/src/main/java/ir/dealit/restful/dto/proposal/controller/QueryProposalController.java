package ir.dealit.restful.dto.proposal.controller;

import ir.dealit.restful.api.query.QueryProposalApi;
import ir.dealit.restful.dto.proposal.Proposal;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QueryProposalController implements QueryProposalApi {
    @Override
    public ResponseEntity<PagedModel<Proposal>> getAllProposals(Pageable pageable, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<PagedModel<Proposal>> getAllProposalsByType(Pageable pageable, String type, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<EntityModel<Proposal>> getProposal(ObjectId id, Authentication authentication) {
        return null;
    }
}
