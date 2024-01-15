package ir.dealit.restful.module.contract.service.impl;

import ir.dealit.restful.dto.contract.Contract;
import ir.dealit.restful.dto.enums.ContractStatus;
import ir.dealit.restful.module.contract.repository.ContractRepository;
import ir.dealit.restful.module.contract.service.ContractService;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;;

    @Override
    public Page<Contract> contracts(Pageable pageable, ContractStatus status, UserEntity user) {
        return null;
    }

    @Override
    public Contract contract(ObjectId id, UserEntity user) {
        return null;
    }

    @Override
    public ObjectId create(Contract contract, UserEntity user) {
        return null;
    }

    @Override
    public void update(ObjectId contractId, Contract contract, UserEntity user) {

    }
}
