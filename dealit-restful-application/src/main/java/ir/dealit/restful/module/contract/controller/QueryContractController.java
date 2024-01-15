package ir.dealit.restful.module.contract.controller;

import ir.dealit.restful.api.query.QueryContractApi;
import ir.dealit.restful.dto.common.ResponseModel;
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

import java.util.List;

import static ir.dealit.restful.util.helper.ControllerResponseHelper.*;

@RestController
@RequiredArgsConstructor
public class QueryContractController implements QueryContractApi {

    private final ContractService contractService;


    @Override
    public ResponseEntity<ResponseModel<List<Contract>>> getAllContracts(Pageable pageable, ContractStatus status, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel<Contract>> getContract(ObjectId id, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel<Workroom>> getWorkroom(ObjectId id, Authentication authentication) {
        return null;
    }
}
