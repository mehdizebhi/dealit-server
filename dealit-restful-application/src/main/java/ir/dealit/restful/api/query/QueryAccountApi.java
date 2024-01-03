package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.account.*;
import ir.dealit.restful.dto.common.ResponseModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/accounts")
public interface QueryAccountApi {

    @GetMapping("/freelancer-info")
    @Secured("ROLE_FREELANCER")
    ResponseEntity<ResponseModel<FreelancerAccountInfo>> getFreelancerAccountInfo(
            Authentication authentication
    );

    @GetMapping("/client-info")
    @Secured("ROLE_CLIENT")
    ResponseEntity<ResponseModel<ClientAccountInfo>> getClientAccountInfo(
            Authentication authentication
    );
}
