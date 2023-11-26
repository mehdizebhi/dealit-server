package ir.dealit.restful.dto.common;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@Builder
public class EndpointInfo extends RepresentationModel<EndpointInfo> {
    private String title;
    private String description;
}
