package ir.dealit.restful.module.job.controller;

import ir.dealit.restful.api.command.CommandProposalApi;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.proposal.ChangeProposal;
import ir.dealit.restful.dto.proposal.NewProposal;
import ir.dealit.restful.module.job.service.ProposalService;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CommandProposalController implements CommandProposalApi {

    private final ProposalService proposalService;

    @Override
    public ResponseEntity<ResponseModel<Map<String, Object>>> sendProposal(NewProposal newProposal, Authentication authentication) {
        var id = proposalService.createNewProposal(newProposal, (UserEntity) authentication.getPrincipal());
        return ResponseEntity.ok(new ResponseModel.Builder<Map<String, Object>>()
                .data(Map.of("id", id.toString()))
                .success()
                .build()
        );
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> updateProposal(ObjectId id, ChangeProposal changeProposal, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> deleteProposal(ObjectId id, Authentication authentication) {
        return null;
    }
}