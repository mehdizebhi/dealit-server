package ir.dealit.restful.module.contract.controller;

import ir.dealit.restful.api.query.QueryContractApi;
import ir.dealit.restful.dto.contract.ClientContractInfo;
import ir.dealit.restful.dto.contract.Contract;
import ir.dealit.restful.dto.contract.FreelancerContractInfo;
import ir.dealit.restful.dto.contract.Workroom;
import ir.dealit.restful.dto.enums.ContractStatus;
import ir.dealit.restful.module.contract.service.ContractService;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import static ir.dealit.restful.util.helper.ControllerResponseHelper.*;

@RestController
@RequiredArgsConstructor
public class QueryContractController implements QueryContractApi {

    private final ContractService contractService;

    @Override
    public ResponseEntity<EntityModel<FreelancerContractInfo>> getFreelancerContractInfo(Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<EntityModel<ClientContractInfo>> getClientContractInfo(Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<PagedModel<Contract>> getAllContracts(Pageable pageable, ContractStatus status, Authentication authentication) {
        var models = toPagedModel(contractService
                .contracts(pageable, status, (UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(models);
    }

    @Override
    public ResponseEntity<EntityModel<Contract>> getContract(ObjectId id, Authentication authentication) {
        var model = EntityModel.of(contractService.contract(id, (UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(model);
    }

    @Override
    public ResponseEntity<EntityModel<Workroom>> getWorkroom(ObjectId id, Authentication authentication) {
        return null;
    }
}
