package ir.dealit.restful.dto.account;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WorkingHours {
    private float hours;
    private LocalDateTime start;
    private LocalDateTime end;
}
