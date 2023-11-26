package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.profile.ProfileStats;
import ir.dealit.restful.dto.profile.FreelancerProfile;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/profile")
public interface QueryProfileApi {

    @GetMapping("/")
    ResponseEntity<EntityModel<FreelancerProfile>> getProfile(
            Authentication authentication
    );

    @GetMapping("/stats")
    ResponseEntity<EntityModel<ProfileStats>> getProfileStats(
        Authentication authentication
    );
}
