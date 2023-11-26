package ir.dealit.restful.module.account.service.impl;

import ir.dealit.restful.api.query.QueryAccountApi;
import ir.dealit.restful.dto.account.*;
import ir.dealit.restful.dto.enums.AccountType;
import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.module.account.entity.ClientAccountEntity;
import ir.dealit.restful.module.account.entity.FreelancerAccountEntity;
import ir.dealit.restful.module.account.repository.AccountRepository;
import ir.dealit.restful.module.account.service.QueryAccountService;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class QueryAccountServiceImpl implements QueryAccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public Optional<EntityModel<AccountOverview>> accountOverview(Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        var model = EntityModel.of(AccountOverview.builder()
                .displayName(user.getDisplayName())
                .email(user.getEmail())
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .types(getAccountTypes(user.getAccounts()))
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build());

        model.add(linkTo(methodOn(QueryAccountApi.class).getAccountStats(authentication)).withRel("stats"));
        model.add(linkTo(methodOn(QueryAccountApi.class).getAccountActivity(authentication)).withRel("activities"));
        return Optional.of(model);
    }

    @Override
    public Optional<EntityModel<TinyStats>> accountStats(Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        var model = EntityModel.of(TinyStats.builder()
                .build());

        return Optional.of(model);
    }

    @Override
    public Optional<EntityModel<WorkingHours>> workingHours(Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        var model = EntityModel.of(WorkingHours.builder()
                .build());

        return Optional.of(model);
    }

    @Override
    public Optional<EntityModel<IncomeInfo>> incomeInfo(Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        var model = EntityModel.of(IncomeInfo.builder()
                .build());

        return Optional.of(model);
    }

    @Override
    public Optional<EntityModel<AccountActivity>> accountActivity(Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        var model = EntityModel.of(AccountActivity.builder()
                .build());

        return Optional.of(model);
    }

    private List<AccountType> getAccountTypes(List<AccountEntity> accountEntities) {
        List<AccountType> types = new ArrayList<>();
        for (AccountEntity accountEntity : accountEntities) {
            if (accountEntity instanceof ClientAccountEntity) {
                types.add(AccountType.CLIENT);
            } else if (accountEntity instanceof FreelancerAccountEntity) {
                types.add(AccountType.FREELANCER);
            }
        }
        return types;
    }
}
