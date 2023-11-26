package ir.dealit.restful.service;

import ir.dealit.restful.dto.common.InitialSource;
import jakarta.annotation.Nullable;
import org.bson.types.ObjectId;
import org.springframework.hateoas.EntityModel;

import java.util.Optional;

public interface EndpointGatewayService {

    Optional<EntityModel<InitialSource>> initialSource(@Nullable ObjectId userId);
}
