package ir.dealit.restful.module.contract.service;

import ir.dealit.restful.dto.contract.Contract;
import ir.dealit.restful.dto.enums.ContractStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ContractService {

    Optional<PagedModel<Contract>> allContracts(Pageable pageable, ContractStatus status, Authentication authentication);
    Optional<EntityModel<Contract>> contract(Authentication authentication);
}
