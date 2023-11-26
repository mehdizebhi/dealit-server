package ir.dealit.restful.api.command;

import ir.dealit.restful.dto.contract.ChangeContract;
import ir.dealit.restful.dto.contract.NewContract;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/contracts")
public interface CommandContractApi {

    @PostMapping("/")
    ResponseEntity<Void> createContract(
            @RequestBody NewContract newContract,
            Authentication authentication
    );

    @PutMapping("/")
    ResponseEntity<Void> updateContract(
            @RequestBody ChangeContract contract,
            Authentication authentication
    );

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteContract(
            @PathVariable("id") ObjectId id,
            Authentication authentication
    );
}