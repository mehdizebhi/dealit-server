package ir.dealit.restful.module.timetracker.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkTimeEntity {
    private String title;
    private Date start;
    private Date end;
}
