package ir.dealit.restful.dto.payment;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
@Builder
public class Payment {
    // Todo: Complete Later
    private Long tCode;
    private Double amount;
    private String from;
    private String to;
    private LocalDateTime date;
}
