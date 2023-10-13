package ir.dealit.restful.util.hateoas.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import ir.dealit.restful.module.user.controller.UserController;
import ir.dealit.restful.api.AccountApi;
import ir.dealit.restful.dto.user.UserInfo;
import ir.dealit.restful.module.account.entity.AccountEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserInfoRepresentationModelAssembler extends
        RepresentationModelAssemblerSupport<UserEntity, UserInfo> {

    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     */
    public UserInfoRepresentationModelAssembler() {
        super(UserController.class, UserInfo.class);
    }

    @Override
    public UserInfo toModel(UserEntity entity) {
        if (Objects.nonNull(entity)) {
            UserInfo model = createModelWithId(entity.getId(), entity);
            BeanUtils.copyProperties(entity, model);
            model.setId(entity.getId().toString());
            model.setAccountsId(entity.getAccounts().stream().map(account -> account.getId().toString()).collect(Collectors.toList()));
            model.add(getLinks(entity));
//            model.add(
//                    linkTo(methodOn(UserController.class).getUserById(entity.getId())).withSelfRel(),
//                    linkTo(methodOn(UserController.class).getAccountOverview(entity.getId())).withRel("accounts"));
            return model;
        }
        return null;
    }

    public List<UserInfo> toListModel(Iterable<UserEntity> entities){
        return null;
    }

    private List<Link> getLinks(UserEntity entity) {
        List<Link> links = new ArrayList<>();
        for (AccountEntity account : entity.getAccounts()) {
            links.add(linkTo(methodOn(AccountApi.class).getAccountOverview(account.getId())).withRel("accounts"));
        }
        return links;
    }
}
