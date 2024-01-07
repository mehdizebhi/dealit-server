package ir.dealit.restful.controller;

import ir.dealit.restful.api.MainApi;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.service.EndpointGatewayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController implements MainApi {

    private final EndpointGatewayService endpointGatewayService;

    @Override
    public ResponseEntity<ResponseModel<Map<String, Object>>> getInitialSource(Authentication authentication) {
        return ResponseEntity.ok(new ResponseModel.Builder<Map<String, Object>>()
                .data(null)
                .message("Hello World!")
                .build()
        );
    }
}




