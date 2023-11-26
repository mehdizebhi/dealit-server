package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.job.FreelancerTalent;
import ir.dealit.restful.dto.job.TalentFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/talents")
public interface QueryTalentApi {

    @GetMapping("/")
    ResponseEntity<PagedModel<FreelancerTalent>> getAllTalents(
            @PageableDefault Pageable pageable,
            Authentication authentication
    );

    @GetMapping("/")
    ResponseEntity<PagedModel<FreelancerTalent>> getAllTalentsByFilter(
            @PageableDefault Pageable pageable,
            Authentication authentication
    );

    // Todo: Complete later
}
