package ir.dealit.restful.api.command;

import ir.dealit.restful.dto.proposal.ChangeProposal;
import ir.dealit.restful.dto.proposal.NewProposal;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/proposals")
public interface CommandProposalApi {

    @PostMapping("/")
    ResponseEntity<Void> createProposal(
            @RequestBody NewProposal newProposal,
            Authentication authentication
    );

    @PutMapping("/")
    ResponseEntity<Void> updateProposal(
            @RequestBody ChangeProposal changeProposal,
            Authentication authentication
    );

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProposal(
            @PathVariable("id") ObjectId id,
            Authentication authentication
    );
}
