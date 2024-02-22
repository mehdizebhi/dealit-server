package ir.dealit.restful.api.command;

import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.proposal.ChangeProposal;
import ir.dealit.restful.dto.proposal.NewProposal;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/v1/proposals")
public interface CommandProposalApi {

    @PostMapping("")
    ResponseEntity<ResponseModel<Map<String, Object>>> sendProposal(
            @RequestBody NewProposal newProposal,
            Authentication authentication
    );

    @PatchMapping("/{id}")
    ResponseEntity<ResponseModel<Void>> updateProposal(
            @PathVariable("id") ObjectId id,
            @RequestBody ChangeProposal changeProposal,
            Authentication authentication
    );

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseModel<Void>> deleteProposal(
            @PathVariable("id") ObjectId id,
            Authentication authentication
    );
}
