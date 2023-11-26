package ir.dealit.restful.dto.common;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
public class InitialSource extends RepresentationModel<InitialSource> {
}
