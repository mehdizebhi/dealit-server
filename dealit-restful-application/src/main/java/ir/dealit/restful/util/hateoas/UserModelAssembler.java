package ir.dealit.restful.util.hateoas;

import ir.dealit.restful.dto.enums.AccountType;
import ir.dealit.restful.dto.user.UserInfo;
import ir.dealit.restful.module.account.controller.QueryAccountController;
import ir.dealit.restful.module.chat.controller.QueryChatController;
import ir.dealit.restful.module.inbox.controller.QueryInboxController;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.wallet.controller.QueryWalletController;
import org.joda.time.DateTime;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler {

    public EntityModel<UserInfo> toModel(Authentication authentication) {
        UserEntity entity = (UserEntity) authentication.getPrincipal();
        var model = EntityModel.of(UserInfo.builder()
                .displayName(entity.getDisplayName())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .phoneNumber(entity.getPhoneNumber())
                .connections(entity.getConnections())
                .confirmedEmail(entity.isEmailConfirmed())
                .confirmedPhone(entity.isPhoneConfirmed())
                .types(entity.getAccountTypes())
                .pictureHref(entity.getPictureHref())
                .createdAt(new DateTime(entity.getCreatedAt()))
                .updatedAt(new DateTime(entity.getUpdatedAt()))
                .build());

        model.add(getLinks(authentication, entity));
        return model;
    }

    private List<Link> getLinks(Authentication authentication, UserEntity entity) {
        List<Link> links = new ArrayList<>();
        links.addAll(getAccountLinks(authentication, entity));
        links.add(linkTo(methodOn(QueryWalletController.class).getWalletInfo(authentication)).withRel("wallet"));
        links.add(linkTo(methodOn(QueryInboxController.class).getInboxInfo(authentication)).withRel("inbox"));
        links.add(linkTo(methodOn(QueryChatController.class).getChatInfo(authentication)).withRel("chat"));

        return links;
    }

    private List<Link> getAccountLinks (Authentication authentication, UserEntity entity) {
        List<Link> links = new ArrayList<>();
        for (var type : entity.getAccountTypes()){
            if (type == AccountType.FREELANCER) {
                links.add(linkTo(methodOn(QueryAccountController.class).getFreelancerAccountInfo(authentication)).withRel("freelancer"));
            } else if (type == AccountType.CLIENT) {
                links.add(linkTo(methodOn(QueryAccountController.class).getClientAccountInfo(authentication)).withRel("client"));
            }
        }
        return links;
    }
}
