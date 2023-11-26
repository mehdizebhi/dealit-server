package ir.dealit.restful.dto.job;

import ir.dealit.restful.dto.contract.Contract;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@Builder
public class JobPosition {
    private String positionId;
    private String title;
    private @Nullable List<JobAd> jobAds;
    private @Nullable List<Contract> contracts;
    private long createdAt;
    private long updatedAt;
}
