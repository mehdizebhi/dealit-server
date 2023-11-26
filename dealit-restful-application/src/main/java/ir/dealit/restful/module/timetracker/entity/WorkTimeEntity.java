package ir.dealit.restful.module.timetracker.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkTimeEntity {
    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
}
