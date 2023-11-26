package ir.dealit.restful.module.contract.service.impl;

import ir.dealit.restful.dto.contract.Contract;
import ir.dealit.restful.dto.enums.ContractStatus;
import ir.dealit.restful.module.contract.repository.ContractRepository;
import ir.dealit.restful.module.contract.service.ContractService;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    @Override
    public Optional<PagedModel<Contract>> allContracts(Pageable pageable, ContractStatus status, Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        Collection<Contract> contracts = new ArrayList<>();
        var models = PagedModel.of(contracts);

        return Optional.of((PagedModel<Contract>) models);
    }

    @Override
    public Optional<EntityModel<Contract>> contract(Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        var model = EntityModel.of(Contract.builder()
                .build());

        return Optional.of(model);
    }
}
