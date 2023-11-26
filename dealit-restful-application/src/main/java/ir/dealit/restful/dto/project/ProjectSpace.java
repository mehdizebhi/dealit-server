package ir.dealit.restful.dto.project;

import ir.dealit.restful.dto.job.JobPosition;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ProjectSpace {
    private String id;
    private String title;
    private List<JobPosition> positions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
