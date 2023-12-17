package ir.dealit.restful.module.contract.service;

import ir.dealit.restful.dto.contract.Contract;
import ir.dealit.restful.dto.enums.ContractStatus;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContractService {

    Page<Contract> contracts(Pageable pageable, ContractStatus status, UserEntity user);
    Contract contract(ObjectId id, UserEntity user);
    Integer countFreelancerContracts(ContractStatus status, UserEntity user);
}
