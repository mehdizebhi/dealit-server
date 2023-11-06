package ir.dealit.restful.util.hateoas.assembler;

import ir.dealit.restful.module.account.controller.AccountController;
import ir.dealit.restful.dto.account.AccountOverview;
import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.module.account.entity.ClientAccountEntity;
import ir.dealit.restful.module.account.entity.FreelancerAccountEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class AccountOverviewModelAssembler {

}
/*public class AccountOverviewModelAssembler extends
        RepresentationModelAssemblerSupport<AccountEntity, AccountOverview> {

    *//**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *//*
    public AccountOverviewModelAssembler() {
        super(AccountController.class, AccountOverview.class);
    }

    @Override
    public AccountOverview toModel(AccountEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        AccountOverview model = createModelWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, model);
        if (entity instanceof FreelancerAccountEntity) {
//            model.setId(entity.getId().toString());
//            model.setAccountsId(entity.getAccounts().stream().map(account -> account.getId().toString()).collect(Collectors.toList()));
//            model.add(getLinks(entity));
            return model;
        } else if (entity instanceof ClientAccountEntity) {
//            model.setId(entity.getId().toString());
//            model.setAccountsId(entity.getAccounts().stream().map(account -> account.getId().toString()).collect(Collectors.toList()));
//            model.add(getLinks(entity));
            return model;
        }
        return null;
    }

    private List<Link> getLinks(AccountEntity entity) {
        List<Link> links = new ArrayList<>();
        return links;
    }
}*/