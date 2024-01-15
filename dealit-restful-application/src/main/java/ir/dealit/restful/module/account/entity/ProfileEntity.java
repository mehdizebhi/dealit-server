package ir.dealit.restful.module.account.entity;

import ir.dealit.restful.dto.profile.Education;
import ir.dealit.restful.dto.profile.Experience;
import ir.dealit.restful.dto.profile.PersonalProject;
import ir.dealit.restful.module.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "profiles")
public class ProfileEntity {
    private @MongoId ObjectId id;
    private String bio;
    private List<PersonalProject> personalProjects;
    private List<Experience> experiences;
    private List<Education> educations;
    private Map<String, String> links;
    private @LastModifiedDate Date updatedAt;
    private @DocumentReference UserEntity owner;

    public ProfileEntity(UserEntity owner) {
        this.owner = owner;
        this.bio = "";
        this.personalProjects = Collections.emptyList();
        this.experiences = Collections.emptyList();
        this.educations = Collections.emptyList();
        this.links = Collections.emptyMap();
    }
}
