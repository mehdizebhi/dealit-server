package ir.dealit.restful.module.account.controller;

import ir.dealit.restful.api.query.QueryProfileApi;
import ir.dealit.restful.dto.profile.FreelancerProfile;
import ir.dealit.restful.dto.profile.ProfileStats;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QueryProfileController implements QueryProfileApi {
    @Override
    public ResponseEntity<EntityModel<FreelancerProfile>> getProfile(Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<EntityModel<ProfileStats>> getProfileStats(Authentication authentication) {
        return null;
    }
}
