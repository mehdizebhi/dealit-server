package ir.dealit.restful.controller;

import ir.dealit.restful.api.InitialApi;
import ir.dealit.restful.dto.common.InitialSource;
import ir.dealit.restful.service.EndpointGatewayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class InitialController implements InitialApi {

    private final EndpointGatewayService endpointGatewayService;

    @Override
    public ResponseEntity<EntityModel<InitialSource>> getInitialSource(Authentication authentication) {
        endpointGatewayService.initialSource(null);
        return null;
    }
}




