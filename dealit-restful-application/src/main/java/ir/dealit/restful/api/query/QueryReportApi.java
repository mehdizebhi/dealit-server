package ir.dealit.restful.api.query;

import ir.dealit.restful.dto.report.Report;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/reports")
public interface QueryReportApi {

    @GetMapping("")
    ResponseEntity<PagedModel<Report>> getAllReports(
            Authentication authentication
    );

    // Todo: which reports we needed?
}
