package ir.dealit.restful.dto.account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewConnection {
    private int number;
    private long timestamp;
}
