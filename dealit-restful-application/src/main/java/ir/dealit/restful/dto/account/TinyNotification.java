package ir.dealit.restful.dto.account;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
public class TinyNotification extends RepresentationModel<TinyNotification> {
    private String color;
    private String header;
    private String text;
}
