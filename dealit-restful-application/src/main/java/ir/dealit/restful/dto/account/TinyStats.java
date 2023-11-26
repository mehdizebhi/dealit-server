package ir.dealit.restful.dto.account;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class TinyStats {
    private String message;
    private Double totalAssets;
    private List<TinyNotification> notifications;
    private @Nullable FreelancerTinyStats freelancer;
    private @Nullable ClientTinyStats client;

    @Data
    @Builder
    public static class FreelancerTinyStats {
        private int activeProposals;
        private int activeJobs;
        private int newInvitations;
        private int totalConnection;
    }

    @Data
    @Builder
    public static class ClientTinyStats {
        private int newProposals;
    }
}
