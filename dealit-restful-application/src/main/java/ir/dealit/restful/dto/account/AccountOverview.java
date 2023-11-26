package ir.dealit.restful.dto.account;

import ir.dealit.restful.dto.enums.AccountType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class AccountOverview {
    private String displayName;
    private String username;
    private String phoneNumber;
    private String email;
    private List<AccountType> types;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


   /* private @NotNull String id;
    private @NotNull String type;
    private @NotNull String number;
    private @NotNull String user;
    private @NotNull String inbox;
    private @NotNull String wallet;
    private @Nullable List<String> activeJobs;
    private @Nullable List<String> proposals;
    private @Nullable List<String> projects;
    private @Nullable List<String> ads;
    private @Nullable List<String> jobs;*/

}
