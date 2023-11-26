package ir.dealit.restful.dto.profile;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@Builder
public class FreelancerProfile {
    private String name;
    private String headline;
    private String bio;
    private String location;
    private double income;
    private float workingHours;
    private short successfulJobs;
    private short currentProject;
    private List<Experience> experiences;
    private List<Education> educations;
    private List<PersonalProject> personalProjects;
}
