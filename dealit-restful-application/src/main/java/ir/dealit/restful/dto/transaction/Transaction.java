package ir.dealit.restful.dto.transaction;

import ir.dealit.restful.dto.enums.TransactionStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
@Builder
public class Transaction {
    private long code;
    private String to;
    private String from;
    private Double amount;
    private TransactionStatus status;
    private LocalDateTime date;
}
