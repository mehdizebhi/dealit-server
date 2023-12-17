package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.account.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/accounts")
public interface QueryAccountApi {

    @GetMapping("/info")
    ResponseEntity<EntityModel<AccountInfo>> getAccountInfo(
            Authentication authentication
    );

    @GetMapping("/stats")
    ResponseEntity<EntityModel<AccountStats>> getAccountStats(
            Authentication authentication
    );

    @GetMapping("/overview")
    ResponseEntity<EntityModel<AccountOverview>> getAccountOverview(
            Authentication authentication
    );

    /*@GetMapping("/freelancer/stats")
    ResponseEntity<EntityModel<TinyStats.FreelancerTinyStats>> getFreelancerStats(
            Authentication authentication
    );

    @GetMapping("/client/stats")
    ResponseEntity<EntityModel<TinyStats.ClientTinyStats>> getClientStats(
            Authentication authentication
    );

    @GetMapping("/notification")
    ResponseEntity<PagedModel<Notification>> getNotifications(
            Authentication authentication
    );

    @GetMapping("/stats")
    ResponseEntity<EntityModel<TinyStats>> getAccountStats(
            Authentication authentication
    );

    @GetMapping("/hours")
    ResponseEntity<EntityModel<WorkingHours>> getWorkingHours(
            Authentication authentication
    );

    @GetMapping("/income")
    ResponseEntity<EntityModel<IncomeInfo>> getIncomeInfo(
            Authentication authentication
    );

    @GetMapping("/activities")
    ResponseEntity<EntityModel<AccountActivity>> getAccountActivity(
            Authentication authentication
    );

    @GetMapping("/connections")
    ResponseEntity<Map<String, Object>> getAccountConnections(
            Authentication authentication
    );*/
}
