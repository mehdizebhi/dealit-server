package ir.dealit.restful.module.report.controller;

import ir.dealit.restful.api.query.QueryReportApi;
import ir.dealit.restful.dto.report.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QueryReportController implements QueryReportApi {
    @Override
    public ResponseEntity<PagedModel<Report>> getAllReports(Authentication authentication) {
        return null;
    }
}
