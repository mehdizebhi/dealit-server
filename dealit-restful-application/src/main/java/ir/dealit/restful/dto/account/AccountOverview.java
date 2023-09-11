package ir.dealit.restful.dto.account;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@Builder
public class AccountOverview extends RepresentationModel<AccountOverview> {

    private @NotNull String id;
    private @NotNull String type;
    private @NotNull String number;
    private @NotNull String user;
    private @NotNull String inbox;
    private @NotNull String wallet;
    private @Nullable List<String> activeJobs;
    private @Nullable List<String> proposals;
    private @Nullable List<String> projects;
    private @Nullable List<String> ads;
    private @Nullable List<String> jobs;

}
