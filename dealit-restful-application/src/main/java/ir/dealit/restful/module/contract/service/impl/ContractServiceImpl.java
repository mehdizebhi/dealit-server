package ir.dealit.restful.module.contract.service.impl;

import ir.dealit.restful.dto.contract.Contract;
import ir.dealit.restful.dto.enums.AccountType;
import ir.dealit.restful.dto.enums.ContractStatus;
import ir.dealit.restful.module.contract.repository.ContractRepository;
import ir.dealit.restful.module.contract.service.ContractService;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final UserAuthService userAuthService;

    @Override
    public Page<Contract> contracts(Pageable pageable, ContractStatus status, UserEntity user) {
        return null;
    }

    @Override
    public Contract contract(ObjectId id, UserEntity user) {
        return null;
    }

    @Override
    public Integer countFreelancerContracts(ContractStatus status, UserEntity user) {
        var accountId = userAuthService.getAccountId(user, AccountType.FREELANCER);
        if (accountId.isPresent()) {
            return contractRepository.countByStatusAndHired(status, accountId.get());
        }
        throw new RuntimeException("There is no AccountStats found");
    }
}
