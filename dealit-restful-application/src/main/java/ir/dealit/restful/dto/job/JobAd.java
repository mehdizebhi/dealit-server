package ir.dealit.restful.dto.job;

import ir.dealit.restful.dto.enums.ExperienceLevel;
import ir.dealit.restful.dto.enums.JobAdStatus;
import ir.dealit.restful.dto.enums.ProjectLength;
import ir.dealit.restful.dto.enums.WeeklyLoad;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobAd {

    private String id;
    private String title;
    private String description;
    private boolean fixedPrice;
    private Double minBudget;
    private Double maxBudget;
    private String ownerId;
    private ProjectLength projectLength;
    private WeeklyLoad weeklyLoad;
    private SubmitRange submitRange;
    private ExperienceLevel experienceLevel;
    private String field;
    private List<String> tags;
    private List<String> questions;
    private List<String> requirements;
    private List<String> skills;
    private JobAdStatus status;
    private String jobPositionId;
    private DateTime createdAt;
    private DateTime updatedAt;
    private int requiredConnection;
}
