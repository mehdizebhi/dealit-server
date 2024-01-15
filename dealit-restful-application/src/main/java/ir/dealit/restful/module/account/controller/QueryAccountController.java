package ir.dealit.restful.module.account.controller;

import ir.dealit.restful.api.query.QueryAccountApi;
import ir.dealit.restful.dto.account.*;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.module.job.controller.QueryProposalController;
import ir.dealit.restful.module.account.service.AccountService;
import ir.dealit.restful.module.contract.controller.QueryContractController;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QueryAccountController implements QueryAccountApi {

    private final AccountService accountService;

    /*@Override
    public ResponseEntity<EntityModel<AccountStats>> getAccountStats(Authentication authentication) {
        var model = EntityModel.of(accountService.accountStats((UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(model);
    }*/

    @Override
    public ResponseEntity<ResponseModel<FreelancerAccountInfo>> getFreelancerAccountInfo(Authentication authentication) {
        var model = new ResponseModel.Builder<FreelancerAccountInfo>()
                .data(accountService.freelancerInfo((UserEntity) authentication.getPrincipal())).success().build();

/*        model.add(linkTo(methodOn(QueryContractController.class).getFreelancerContractInfo(authentication)).withRel("contracts"));
        model.add(linkTo(methodOn(QueryProposalController.class).getFreelancerProposalInfo(authentication)).withRel("proposals"));
        model.add(linkTo(methodOn(QueryProfileController.class).getFreelancerProfileInfo(authentication)).withRel("profile"));*/

        return ResponseEntity.ok(model);
    }

    @Override
    public ResponseEntity<ResponseModel<ClientAccountInfo>> getClientAccountInfo(Authentication authentication) {
        var model = new ResponseModel.Builder<ClientAccountInfo>()
                .data(accountService.clientInfo((UserEntity) authentication.getPrincipal())).success().build();

        /*model.add(linkTo(methodOn(QueryContractController.class).getClientContractInfo(authentication)).withRel("contracts"));
        model.add(linkTo(methodOn(QueryProposalController.class).getClientProposalInfo(authentication)).withRel("proposals"));
        model.add(linkTo(methodOn(QueryProfileController.class).getClientProfileInfo(authentication)).withRel("profile"));*/

        return ResponseEntity.ok(model);
    }

    /*@Override
    public ResponseEntity<EntityModel<TinyStats.FreelancerTinyStats>> getFreelancerStats(Authentication authentication) {
        var model = EntityModel.of(accountService
                .freelancerStats((UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(model);
    }

    @Override
    public ResponseEntity<EntityModel<TinyStats.ClientTinyStats>> getClientStats(Authentication authentication) {
        var model = EntityModel.of(accountService
                .clientStats((UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(model);
    }

    @Override
    public ResponseEntity<PagedModel<Notification>> getNotifications(Authentication authentication) {
        var models = toPagedModel(accountService
                .notifications(PageRequest.of(0, 5), (UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(models);
    }

    @Override
    public ResponseEntity<EntityModel<TinyStats>> getAccountStats(Authentication authentication) {
        var model = EntityModel.of(accountService
                .accountStats((UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(model);
    }

    @Override
    public ResponseEntity<EntityModel<WorkingHours>> getWorkingHours(Authentication authentication) {
        var model = EntityModel.of(accountService
                .workingHours((UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(model);
    }

    @Override
    public ResponseEntity<EntityModel<IncomeInfo>> getIncomeInfo(Authentication authentication) {
        var model = EntityModel.of(accountService
                .incomeInfo((UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(model);
    }

    @Override
    public ResponseEntity<EntityModel<AccountActivity>> getAccountActivity(Authentication authentication) {
        var model = EntityModel.of(accountService
                .accountActivity((UserEntity) authentication.getPrincipal()));

        return ResponseEntity.ok(model);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAccountConnections(Authentication authentication) {
        return null;
    }*/
}
