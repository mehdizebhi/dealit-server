package ir.dealit.restful.dto.job;

import ir.dealit.restful.repository.entity.ExperienceLevel;
import ir.dealit.restful.repository.entity.FieldEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobAd {

    private String title;
    private String description;
    private boolean isFixedPrice;
    private Double minPrice;
    private Double maxPrice;
    private ExperienceLevel experienceLevel;
    private FieldEntity field;
    private int projectLength;
    private int weeklyLoad;
    private List<MultipartFile> files;
    private String ownerId;
}
