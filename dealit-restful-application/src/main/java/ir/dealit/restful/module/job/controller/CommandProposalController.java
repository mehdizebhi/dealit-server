package ir.dealit.restful.module.job.controller;

import ir.dealit.restful.api.command.CommandProposalApi;
import ir.dealit.restful.dto.proposal.ChangeProposal;
import ir.dealit.restful.dto.proposal.NewProposal;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommandProposalController implements CommandProposalApi {
    @Override
    public ResponseEntity<Void> createProposal(NewProposal newProposal, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateProposal(ChangeProposal changeProposal, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteProposal(ObjectId id, Authentication authentication) {
        return null;
    }
}
