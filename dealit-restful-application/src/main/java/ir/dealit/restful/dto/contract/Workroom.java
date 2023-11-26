package ir.dealit.restful.dto.contract;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
public class Workroom extends RepresentationModel<Workroom> {
}
