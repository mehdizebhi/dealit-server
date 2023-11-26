package ir.dealit.restful.service.impl;

import ir.dealit.restful.dto.common.EndpointInfo;
import ir.dealit.restful.dto.common.InitialSource;
import ir.dealit.restful.service.EndpointGatewayService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EndpointGatewayServiceImpl implements EndpointGatewayService {
    @Override
    public Optional<EntityModel<InitialSource>> initialSource(@Nullable ObjectId userId) {
        return Optional.empty();
    }

    /* @Override
    public Optional<List<EndpointInfo>> initialSource(@Nullable ObjectId userId) {
        if (userId == null) {

            return Optional.empty();
        }

        return Optional.empty();
    }*/
}
