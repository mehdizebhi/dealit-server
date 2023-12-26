package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.contract.ClientContractInfo;
import ir.dealit.restful.dto.contract.Contract;
import ir.dealit.restful.dto.contract.FreelancerContractInfo;
import ir.dealit.restful.dto.contract.Workroom;
import ir.dealit.restful.dto.enums.ContractStatus;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/contracts")
public interface QueryContractApi {

    @GetMapping("/freelancer/info")
    ResponseEntity<EntityModel<FreelancerContractInfo>> getFreelancerContractInfo(
            Authentication authentication
    );

    @GetMapping("/client/info")
    ResponseEntity<EntityModel<ClientContractInfo>> getClientContractInfo(
            Authentication authentication
    );

    @GetMapping("/")
    ResponseEntity<PagedModel<Contract>> getAllContracts(
            @PageableDefault Pageable pageable,
            @RequestParam(value = "status", required = false) ContractStatus status,
            Authentication authentication
    );

    @GetMapping("/{id}")
    ResponseEntity<EntityModel<Contract>> getContract(
            @PathVariable("id") ObjectId id,
            Authentication authentication
    );

    @GetMapping("/{id}/workroom")
    ResponseEntity<EntityModel<Workroom>> getWorkroom(
            @PathVariable("id") ObjectId id,
            Authentication authentication
    );
}
