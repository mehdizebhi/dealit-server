package ir.dealit.restful.module.account.controller;

import ir.dealit.restful.api.query.QueryTalentApi;
import ir.dealit.restful.dto.job.FreelancerTalent;
import ir.dealit.restful.dto.job.TalentFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QueryTalentController {
}
