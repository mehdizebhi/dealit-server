package ir.dealit.restful.module.contract.controller;

import ir.dealit.restful.api.query.QueryContractApi;
import ir.dealit.restful.dto.contract.Contract;
import ir.dealit.restful.dto.contract.Workroom;
import ir.dealit.restful.dto.enums.ContractStatus;
import ir.dealit.restful.module.contract.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
public class QueryContractController implements QueryContractApi {

    private final ContractService contractService;

    @Override
    public ResponseEntity<PagedModel<Contract>> getAllContracts(Pageable pageable, ContractStatus status, Authentication authentication) {
        return contractService
                .allContracts(pageable, status, authentication)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @Override
    public ResponseEntity<EntityModel<Contract>> getContract(ObjectId id, Authentication authentication) {
        return contractService
                .contract(authentication)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @Override
    public ResponseEntity<EntityModel<Workroom>> getWorkroom(ObjectId id, Authentication authentication) {
        return null;
    }
}
