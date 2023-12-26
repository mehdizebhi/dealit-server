package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.profile.ClientProfile;
import ir.dealit.restful.dto.profile.ProfileStats;
import ir.dealit.restful.dto.profile.FreelancerProfile;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/profiles")
public interface QueryProfileApi {

    @GetMapping("/freelancer")
    @Secured("ROLE_FREELANCER")
    ResponseEntity<EntityModel<FreelancerProfile>> getFreelancerProfileInfo(
            Authentication authentication
    );

    @GetMapping("/client")
    @Secured("ROLE_CLIENT")
    ResponseEntity<EntityModel<ClientProfile>> getClientProfileInfo(
            Authentication authentication
    );

    @GetMapping("/stats")
    ResponseEntity<EntityModel<ProfileStats>> getProfileStats(
        Authentication authentication
    );
}
