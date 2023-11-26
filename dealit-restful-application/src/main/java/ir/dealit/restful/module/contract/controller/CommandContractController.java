package ir.dealit.restful.module.contract.controller;

import ir.dealit.restful.api.command.CommandContractApi;
import ir.dealit.restful.dto.contract.ChangeContract;
import ir.dealit.restful.dto.contract.NewContract;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommandContractController implements CommandContractApi {
    @Override
    public ResponseEntity<Void> createContract(NewContract newContract, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateContract(ChangeContract contract, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteContract(ObjectId id, Authentication authentication) {
        return null;
    }
}
