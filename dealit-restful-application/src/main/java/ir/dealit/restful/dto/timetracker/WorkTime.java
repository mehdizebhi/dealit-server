package ir.dealit.restful.dto.timetracker;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
public class WorkTime extends RepresentationModel<WorkTime> {
    private String title;
    private String contractID;
    private long start;
    private long end;
}
